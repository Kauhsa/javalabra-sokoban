/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.core;

import java.util.ArrayList;

/**
 *
 * @author mika
 */
public class WorldMovementHandler {

    private final World world;

    public WorldMovementHandler(World world) {
        this.world = world;
    }

    public boolean move(WorldObject worldObject, Direction direction) {
        Point currentPoint = worldObject.getPosition();
        Point adjacentPoint;

        /*
         * FIXME: This is pretty ugly. And the whole method is too long - split
         * it at some point.
         */
        try {
            adjacentPoint = currentPoint.applyDirection(direction);
        } catch (IllegalArgumentException e) {
            // the point is apparently negative - no movement there
            return false;
        }

        // we can't move if the point would be out of bounds
        if (!world.isPointInWorld(adjacentPoint)) {
            return false;
        }

        // can the object move to the space next to it
        boolean canMove = true;
        for (WorldObject adjacentWorldObject : world.getWorldObjectsInPoint(adjacentPoint)) {
            if (!adjacentWorldObject.canMove(worldObject)) {
                canMove = false;
                break;
            }
        }

        if (canMove) {
            // if movement is possible, that's enough for us
            world.relocateWorldObject(worldObject, worldObject.getPosition().applyDirection(direction));
            return true;
        } else {
            /*
             * Otherwise, let's try pushing instead.
             * 
             * There are some problems in this pushing system - if there is
             * multiple pushable objects in same position, this will probably
             * not do what is excepted, as the pushing of all those objects
             * won't happen at the same time.
             * 
             * Also if there is tile where you can't move and an pushable object
             * in same position, push will happen but you can't move to the
             * position.
             * 
             * Should be fine for basic sokoban as such situations should not
             * happen there, but needs rework if something fancier is
             * implemented.
             * 
             * The next two lines are to avoid ConcurrentModificationException -
             * there could possibly be cleaner solution though.
             */
            ArrayList<WorldObject> adjacentWorldObjects = new ArrayList<WorldObject>();
            adjacentWorldObjects.addAll(world.getWorldObjectsInPoint(adjacentPoint));
            
            boolean didPushesSucceed = true;
            boolean atLeastOnePush = false;
            
            for (WorldObject adjacentWorldObject : adjacentWorldObjects) {
                if (adjacentWorldObject.canPush(worldObject)) {
                    didPushesSucceed = move(adjacentWorldObject, direction) && didPushesSucceed;
                    atLeastOnePush = atLeastOnePush || didPushesSucceed;
                }
            }

            if (didPushesSucceed && atLeastOnePush) {
                // pushing done, let's try to move again
                return move(worldObject, direction);
            } else {
                return false;
            }
        }
    }
}

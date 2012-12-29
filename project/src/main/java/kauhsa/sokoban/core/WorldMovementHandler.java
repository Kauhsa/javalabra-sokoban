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
      
        // The point where we are trying to more is negative
        if (currentPoint.appliedDirectionWouldBeInvalid(direction)) {
            return false;
        }
        Point adjacentPoint = currentPoint.applyDirection(direction);                
        
        // The point where we are trying to move is out of the bounds
        if (!world.isPointInWorld(adjacentPoint)) {
            return false;
        }

        // If movement is possible, we can just move the worldObject
        if (checkIfMovementPossible(worldObject, direction)) {
            worldObject.relocate(worldObject.getPosition().applyDirection(direction));
            return true;
        }
        
        // Otherwise we will try pushing it instead
        return push(worldObject, direction);        
    }

    private boolean push(WorldObject worldObject, Direction direction) {
        /*
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
         */
        Point adjacentPoint = worldObject.getPosition().applyDirection(direction);

        /* 
         * This is to avoid ConcurrentModificationException - there could
         * possibly be cleaner solution though.
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
            // Pushing done, let's try to move again
            return move(worldObject, direction);
        } else {
            return false;
        }
    }

    private boolean checkIfMovementPossible(WorldObject worldObject, Direction direction) {
        Point adjacentPoint = worldObject.getPosition().applyDirection(direction);
        
        // Check if object can be in the space next to it without pushing
        boolean canMove = true;
        for (WorldObject adjacentWorldObject : world.getWorldObjectsInPoint(adjacentPoint)) {
            if (!adjacentWorldObject.canMove(worldObject)) {
                canMove = false;
                break;
            }
        }
        return canMove;
    }
}

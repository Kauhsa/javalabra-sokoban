package kauhsa.sokoban.core;

import java.util.ArrayList;
import kauhsa.sokoban.core.utils.Direction;
import kauhsa.sokoban.core.utils.Point;

/**
 * Class for implementing movement and pushing logic for WorldObjects.
 */
public class WorldMovementHandler {

    private final World world;
    
    /**
     * Initialize WorldMovementHandler.
     * 
     * @param world World where we do movement.
     */
    public WorldMovementHandler(World world) {
        this.world = world;
    }
    
    /**
     * Move WorldObject one tile to wanted direction, and if necessary, push
     * objects while doing it.
     * 
     * This will apply whatever rules are defined between WorldObjects
     * concerning movement.
     * 
     * @param worldObject WorldObject we want to move.
     * @param direction Direction where we want to move.
     * @return true if movement was successful, otherwise false.
     */
    public boolean move(WorldObject worldObject, Direction direction) {
        Point currentPoint = worldObject.getPosition();

        if (!isAdjacentPointValid(currentPoint, direction)) {
            return false;
        }
        
        // If movement is possible, we can just move the worldObject
        if (checkIfMovementPossible(worldObject, direction)) {
            worldObject.relocate(currentPoint.applyDirection(direction));
            return true;
        }

        // Otherwise we will try pushing it instead
        return push(worldObject, direction);
    }

    private boolean push(WorldObject worldObject, Direction direction) {
        /*
         * There are some problems in this pushing system - if there is
         * multiple pushable objects in same position, this will probably not do
         * what is excepted as the pushing of all those objects won't happen at 
         * the same time. One objects will be pushed, and second one will stay 
         * where it is.
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

    private boolean isAdjacentPointValid(Point currentPoint, Direction direction) {
        // The point where we are trying to more is negative
        if (currentPoint.wouldAppliedDirectionBeInvalid(direction)) {
            return false;
        }
        
        Point adjacentPoint = currentPoint.applyDirection(direction);
        
        // The point where we are trying to move is out of the bounds
        if (!world.isPointInWorld(adjacentPoint)) {
            return false;
        }
        
        // If the point is empty and there are absolutely no objects, don't
        // allow moving
        if (world.getWorldObjectsInPoint(adjacentPoint).isEmpty()) {
            return false;
        }
        
        return true;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.core;

/**
 *
 * @author mika
 */
public class WorldMovementHandler {
    private final World world;
    
    public WorldMovementHandler(World world) {
        this.world = world;
    }
    
    public void move(WorldObject worldObject, Direction direction) {
        if (isMovementPossible(worldObject, direction)) {
            world.relocateWorldObject(worldObject, worldObject.getPosition().applyDirection(direction));
        }
    }
    
    private boolean isMovementPossible(WorldObject worldObject, Direction direction) {
        Point currentPoint = worldObject.getPosition();
        Point adjacentPoint;
        
        // FIXME: ugly
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
        
        // if movement is possible, that's enough for us
        if (canMove) {
            return true;
        } else {
            return false;
        }
        
        // TODO: pushing (and maybe refactoring a bit)
    }
    
}

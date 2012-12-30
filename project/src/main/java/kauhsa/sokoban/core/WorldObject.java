package kauhsa.sokoban.core;

import kauhsa.sokoban.core.utils.Point;

/**
 * Base class for everything the map can contain.
 * 
 * This includes things like floors and walls, but even things like player.
 * 
 * @author mika
 */
public abstract class WorldObject {
    private World world;
    private Point position;
    
    /**
     * Get the WorldObjectType of this object.
     * 
     * @return WorldObjectType of this object.
     */
    public abstract WorldObjectType getType();
    
    /**
     * Can the worldObject given as parameter move to same tile where this
     * object is.
     * 
     * For example, player object can move to tile where an floor object exist, 
     * but player object can not move to tile containing wall object.
     * 
     * @param worldObject tested object.
     * @return true if the two objects can coexist in same tile, otherwise
     * false.
     */
    public abstract boolean canMove(WorldObject worldObject);
    
    /**
     * Can the worldObject given as parameter push this object, if it would be
     * next to this worldObject.
     * 
     * For example, player can push boxes, but boxes can't push boxes.
     * 
     * @param worldObject tested object.
     * @return true if worldObject can push this object, otherwise false.
     */
    public abstract boolean canPush(WorldObject worldObject);
    
    protected void setWorld(World world) {
        if (this.world != null) {
            throw new IllegalStateException("The world can be assigned only once");
        } else if (world == null) {
            throw new IllegalArgumentException("WorldObject can't be assigned to null world");
        }
        
        this.world = world;
    }
    
    protected void setPosition(Point point) {
        if (this.world == null) {
            throw new IllegalStateException("The world is not set for this WorldObject");
        } else if (!this.world.isPointInWorld(point)) {
            throw new IllegalArgumentException("Invalid Point for World of this WorldObject");
        }
        
        this.position = point;
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public void relocate(Point point) {
        this.world.relocateWorldObject(this, point);
    }
    
    
}

package kauhsa.sokoban.core;

import kauhsa.sokoban.core.utils.Point;

/**
 * Abstract base class for different things that can exist in single tile in the
 * World.
 * 
 * This includes things like floors and walls, but even things like
 * player. These things subclass this class.
 */
public abstract class WorldObject {
    private World world;
    private Point position;
    
    /**
     * Get the WorldObjectType of this object. This should not ever change
     * during the lifetime of WorldObject - if it does, behavior is undefined.
     * 
     * @return WorldObjectType of this WorldObject.
     */
    public abstract WorldObjectType getType();
    
    /**
     * Can the worldObject given as parameter move to same tile where this
     * object is.
     * 
     * For example, player object can move to tile where an floor object exist, 
     * but player object can not move to tile containing wall object.
     * 
     * This should not take any current positions of the WorldObjects in to
     * consideration.
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
     * This should not take any current positions of the WorldObjects in to
     * consideration.
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
    
    /**
     * Get the current position of this WorldObject.
     * 
     * @return current position of this WorldObject.
     */
    public Point getPosition() {
        return this.position;
    }
    
    /**
     * Get the world this WorldObject is bound to.
     *
     * @return world where this WorldObject is bound to.
     */
    public World getWorld() {
        return this.world;
    }
    
    /**
     * Relocate this WorldObject in the another Point in this World.
     * 
     * Just a convenience method - you can use relocateWorldObject in {@link
     * World} instead.
     * 
     * @see World.relocateWorldObject
     */
    public void relocate(Point point) {
        this.world.relocateWorldObject(this, point);
    }
    
    
}

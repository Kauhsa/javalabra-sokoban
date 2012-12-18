package kauhsa.sokoban.core.worldobjects;

/**
 * Base class for everything the map can contain.
 * 
 * This includes things like floors and walls, but even things like player.
 * 
 * @author mika
 */
public abstract class WorldObject {
    
    /**
     * Get the WorldObjectType of this object.
     * 
     * @return WorldObjectType of this object.
     */
    public abstract WorldObjectType getType();
    
    /**
     * Can the object given as parameter move to same tile where this object is.
     * 
     * For example, player object can move to tile where an floor object exist, 
     * but player object can not move to tile containing wall object.
     * 
     * @param worldObject tested object.
     * @return true if the two objects can coexist in same tile, otherwise
     * false.
     */
    public abstract boolean canMove(WorldObject worldObject);
    
    
}

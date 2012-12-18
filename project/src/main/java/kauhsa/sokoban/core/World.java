package kauhsa.sokoban.core;

import kauhsa.sokoban.core.worldobjects.WorldObject;

/**
 * This class contains the WorldObjects and provides methods for accessing them.
 * 
 * @author mika
 */
public class World {
    private final int height;
    private final int width;
    
    public World(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Width and height of the world has to be at least 1");
        }
        
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    /**
     * Check if the point exist in this world.
     * 
     * In other words, check is the point too far in X or Y axis to be inside of
     * this world.
     * 
     * @param point
     */
    public boolean isPointInWorld(Point point) {
        /*
         * No need to check for negative coordinates, as Point object can't have
         * them in the first place.
         */
        if (point.getX() > this.getWidth() - 1 || point.getY() > this.getHeight() - 1) {
            return false;
        } 
        
        return true;
    }
    
    /**
     * Place the newly created object to this World.
     * 
     * This should be done only once to each WorldObject.
     * 
     * @param point
     * @param object
     */
    public void placeWorldObject(Point point, WorldObject object) {
        object.setWorld(this);
        object.setPosition(point);    
    }
}

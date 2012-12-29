package kauhsa.sokoban.core;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class contains the WorldObjects and provides methods for accessing them.
 * 
 * @author mika
 */
public class World {
    private static final int INITIAL_CAPACITY_FOR_EACH_TYPE = 50;
    private static final int INITIAL_CAPACITY_FOR_EACH_POINT = 2;
    
    private final int height;
    private final int width;    
    private Set<WorldObject>[][] worldObjectGrid;
    private Map<WorldObjectType, Set<WorldObject>> worldObjectsByType;
    
    public World(int width, int height) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Width and height of the world has to be at least 1");
        }
        
        this.height = height;
        this.width = width;
        
        InitalizeWorldObjectsByType();
        InitalizeWorldObjectGrid();
    }

    private void InitalizeWorldObjectsByType() {
        worldObjectsByType = new EnumMap<WorldObjectType, Set<WorldObject>>(WorldObjectType.class);
        
        for (WorldObjectType type : WorldObjectType.values()) {
            worldObjectsByType.put(type, new HashSet<WorldObject>(INITIAL_CAPACITY_FOR_EACH_TYPE));
        }
    }
    
    private void InitalizeWorldObjectGrid() {
        worldObjectGrid = new Set[this.width][this.height];
        
        for (Point point : this.getPoints()) {
            worldObjectGrid[point.getX()][point.getY()] = new HashSet<WorldObject>(INITIAL_CAPACITY_FOR_EACH_POINT);
        }
    }
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public Set<WorldObject> getWorldObjectsInPoint(Point point) {
        return worldObjectGrid[point.getX()][point.getY()];
    }
    
    public Set<WorldObject> getWorldObjectsOfType(WorldObjectType type) {
        return worldObjectsByType.get(type);
    }
    
    public Iterable<Point> getPoints() {
        return new PointIterator(this.width, this.height);
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
        return point.pointExistInArray(this.width, this.height);
    }
    
    /**
     * Place the newly created object to this World.
     * 
     * This should be done only once to each WorldObject.
     * 
     * @param point
     * @param object
     */
    public void placeWorldObject(WorldObject object, Point point) {
        object.setWorld(this);
        object.setPosition(point);
        worldObjectsByType.get(object.getType()).add(object);
        getWorldObjectsInPoint(object.getPosition()).add(object);        
    }
    
    public void relocateWorldObject(WorldObject object, Point point) {
        getWorldObjectsInPoint(object.getPosition()).remove(object);
        getWorldObjectsInPoint(point).add(object);
        object.setPosition(point);
    }
}

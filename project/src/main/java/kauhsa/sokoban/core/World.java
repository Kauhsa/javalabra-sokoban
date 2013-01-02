package kauhsa.sokoban.core;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kauhsa.sokoban.core.utils.Point;
import kauhsa.sokoban.core.utils.PointIterator;

/**
 * Represents two-dimensional grid, where each square can contain one or more
 * {@link WorldObject}s.
 */
public class World {
    private static final int INITIAL_CAPACITY_FOR_EACH_TYPE = 50;
    private static final int INITIAL_CAPACITY_FOR_EACH_POINT = 2;
    
    private final int height;
    private final int width;    
    private Set<WorldObject>[][] worldObjectGrid;
    private Map<WorldObjectType, List<WorldObject>> worldObjectsByType;
    
    /**
     * Create new world.
     * 
     * Width and height of the world has to be 1 or greater.
     * 
     * @param width width of the grid
     * @param height height of the grid
     * @throws IllegalArgumentException if width or height is lesser than 1
     */
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
        worldObjectsByType = new EnumMap<WorldObjectType, List<WorldObject>>(WorldObjectType.class);
        
        for (WorldObjectType type : WorldObjectType.values()) {
            worldObjectsByType.put(type, new ArrayList<WorldObject>(INITIAL_CAPACITY_FOR_EACH_TYPE));
        }
    }
    
    private void InitalizeWorldObjectGrid() {
        worldObjectGrid = new Set[this.width][this.height];
        
        for (Point point : this.getPoints()) {
            worldObjectGrid[point.getX()][point.getY()] = new HashSet<WorldObject>(INITIAL_CAPACITY_FOR_EACH_POINT);
        }
    }
    
    /**
     * Get height of the world.
     * 
     * @return height of the world.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get width of the world.
     * 
     * @return width of the world.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Get Set of {@link WorldObject}s in specific point in grid.
     * 
     * It is possible to modify the returned Set, but doing so will probably
     * cause World to not work correctly.
     * 
     * @param point Point in the world.
     * @return Set of WorldObjects in the point given as parameter.
     */
    public Set<WorldObject> getWorldObjectsInPoint(Point point) {
        if (!isPointInWorld(point)) {
            throw new IllegalArgumentException("Point is not inside this world");
        }
        
        return worldObjectGrid[point.getX()][point.getY()];
    }
    
    /**
     * Get List of {@link WorldObject}s of specific type that exist in world.
     * 
     * It is possible to modify the returned List, but doing so will probably
     * cause World to not work correctly.
     * 
     * @param type Type of returned WorldObjects.
     * @return WorldObjects of type given as parameter.
     */
    public List<WorldObject> getWorldObjectsOfType(WorldObjectType type) {
        return worldObjectsByType.get(type);
    }
    
    /**
     * Get Iterable of all {@link Point} objects that can possibly exist in this
     * world.
     * 
     * Useful when you want to do something for every Point in this world.
     * 
     * @return Iterable of all Points in this world
     * @see PointIterator
     */
    public Iterable<Point> getPoints() {
        return new PointIterator(this.width, this.height);
    }
    
    private void pointNullCheck(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point can not be null");
        }
    }
    
    private void worldObjectNullCheck(WorldObject wo) {
        if (wo == null) {
            throw new IllegalArgumentException("WorldObject can not be null");
        }
    }
    
    private void worldObjectInThisWorldCheck(WorldObject wo) {
        worldObjectNullCheck(wo);
        if (wo.getWorld() != this) {
            throw new IllegalArgumentException("WorldObject must be bound to this World");
        }
    }
    
    private void pointInThisWorldCheck(Point point) {
        pointNullCheck(point);
        if (!isPointInWorld(point)) {
            throw new IllegalArgumentException("Point must be in this world");
        }
    }
    
    /**
     * Check if the {@link Point} exists in this world.
     * 
     * In other words, check is the point too far in X or Y axis to be inside of
     * this world - does not check for negative indices, as you can not create
     * Point with negative indices in the first place.
     * 
     * @param point Point to be checked.
     * @return true if the Point exists in this world, otherwise false. Also
     * returns false if point is null.
     */
    public boolean isPointInWorld(Point point) {
        if (point == null) {
            return false;
        }
        return point.pointExistInArray(this.width, this.height);
    }
    
    /**
     * Place the newly created object to this World.
     * 
     * Binds the WorldObject to this World - thus can only be done once for each
     * WorldObject.
     * 
     * @param object WorldObject that is placed to World.
     * @param point Point in world where to object will be placed.
     * @throws IllegalArgumentException if Point is invalid for this World or null
     * @throws IllegalArgumentException if WorldObject is already placed in some
     * World or is null.
     */
    public void placeWorldObject(WorldObject object, Point point) {
        pointNullCheck(point);
        worldObjectNullCheck(object);
                
        object.setWorld(this);
        object.setPosition(point);
        worldObjectsByType.get(object.getType()).add(object);
        getWorldObjectsInPoint(object.getPosition()).add(object);        
    }
    
    /**
     * Move WorldObject that already is in this World to another Point.
     * 
     * This method does not apply any rules regarding moving objects.
     * 
     * @param object Object to be moved.
     * @param point The point object will be moved.
     * @throws IllegalArgumentException if Point is invalid for this World or null
     * @throws IllegalArgumentException if WorldObject is not in this world or is null.
     */
    public void relocateWorldObject(WorldObject object, Point point) {
        worldObjectInThisWorldCheck(object);
        pointInThisWorldCheck(point);
        
        getWorldObjectsInPoint(object.getPosition()).remove(object);
        getWorldObjectsInPoint(point).add(object);
        object.setPosition(point);
    }
}

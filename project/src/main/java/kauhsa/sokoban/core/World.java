package kauhsa.sokoban.core;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kauhsa.sokoban.core.worldobjects.WorldObject;
import kauhsa.sokoban.core.worldobjects.WorldObjectType;

/**
 * This class contains the WorldObjects and provides methods for accessing them.
 * 
 * @author mika
 */
public class World {
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    private void InitalizeWorldObjectsByType() {
        worldObjectsByType = new EnumMap<WorldObjectType, Set<WorldObject>>(WorldObjectType.class);
        
        for (WorldObjectType type : WorldObjectType.values()) {
            worldObjectsByType.put(type, new HashSet<WorldObject>());
        }
    }
    
    private void InitalizeWorldObjectGrid() {
        worldObjectGrid = new Set[this.width][this.height];
        Iterator<Point> pointIterator = this.getPointIterator();
        
        while (pointIterator.hasNext()) {
            Point point = pointIterator.next();
            worldObjectGrid[point.getX()][point.getY()] = new HashSet<WorldObject>();
        }
    }
    
    public Collection<WorldObject> getWorldObjectsInPoint(Point point) {
        return Collections.unmodifiableCollection(worldObjectGrid[point.getX()][point.getY()]);
    }
    
    public Collection<WorldObject> getWorldObjectsOfType(WorldObjectType type) {
        return Collections.unmodifiableCollection(worldObjectsByType.get(type));
    }
    
    public Iterator<Point> getPointIterator() {
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
    public void placeWorldObject(Point point, WorldObject object) {
        object.setWorld(this);
        object.setPosition(point);        
    }
}

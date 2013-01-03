/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.core;

import java.util.HashSet;
import java.util.Set;
import kauhsa.sokoban.core.utils.Point;

/**
 * Class for getting information about the current status of World.
 */
public class WorldStatus {
    
    private final World world;
    
    /**
     * Initialize WorldStatus.
     * 
     * @param world World whose status this class gives.
     */
    public WorldStatus(World world) {
        this.world = world;
    }
    
    private Set<Point> getPointsOfWorldObjectType(WorldObjectType worldObjectType) {
        Set<Point> points = new HashSet<Point>();
        
        for (WorldObject worldObject : world.getWorldObjectsOfType(worldObjectType)) {
            points.add(worldObject.getPosition());
        }
        
        return points;
    }
    
    /**
     * Get information about the finished status of this World.
     * 
     * @return true if world is currently on finished status - in other words,
     * if there is an box in every box target.
     */
    public boolean isFinished() {
        Set<Point> boxPoints = getPointsOfWorldObjectType(WorldObjectType.BOX);
        Set<Point> targetPoints = getPointsOfWorldObjectType(WorldObjectType.BOXTARGET);
        
        return (boxPoints.containsAll(targetPoints));
    }
    
}

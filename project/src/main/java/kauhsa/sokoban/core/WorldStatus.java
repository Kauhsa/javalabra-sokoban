/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;
import kauhsa.sokoban.core.utils.Point;

/**
 *
 * @author mika
 */
public class WorldStatus {
    
    private final World world;
    
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
    
    public boolean isFinished() {
        Set<Point> boxPoints = getPointsOfWorldObjectType(WorldObjectType.BOX);
        Set<Point> targetPoints = getPointsOfWorldObjectType(WorldObjectType.BOXTARGET);
        
        return (boxPoints.containsAll(targetPoints));
    }
    
}

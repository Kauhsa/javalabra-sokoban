/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.core;
import java.util.ArrayList;
import java.util.Collection;
import kauhsa.sokoban.core.worldobjects.Box;
import kauhsa.sokoban.core.worldobjects.Floor;
import kauhsa.sokoban.core.worldobjects.Player;
import kauhsa.sokoban.core.worldobjects.Wall;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mika
 */
public class WorldObjectMovementTest {
    private World world;
    private WorldMovementHandler worldMovementHandler;
    
    @Before
    public void setUp() {
        world = new World(3, 3);        
        worldMovementHandler = new WorldMovementHandler(world);   
        
        fillWorldWithFloor();
    }
    
    private void fillWorldWithFloor() {
        for (Point point : world.getPoints()) {
            Floor floor = new Floor();
            world.placeWorldObject(floor, point);
        }
    }
    
    private Player placePlayer(Point point) {
        Player player = new Player();
        world.placeWorldObject(player, point);
        return player;
    }
    
    private Wall placeWall(Point point) {
        Wall wall = new Wall();
        world.placeWorldObject(wall, point);
        return wall;
    }
    
    private Box placeBox(Point point) {
        Box box = new Box();
        world.placeWorldObject(box, point);
        return box;
    }
    
    private void checkWorldObjectIsInPoint(WorldObject worldObject, Point point) {
        boolean isWorldObjectInPoint = world.getWorldObjectsInPoint(point).contains(worldObject);
        String assertMessage = String.format("WorldObject should now be in %s", point);
        assertTrue(assertMessage, isWorldObjectInPoint);        
        assertEquals(point, worldObject.getPosition()); // Just to be extra sure
    }
    
    private void checkMovementAndNewPoint(WorldObject worldObject, Direction direction, Point point) {
        worldMovementHandler.move(worldObject, direction);
        checkWorldObjectIsInPoint(worldObject, point);
    }
    
    @Test
    public void testCanNotMoveOutsideWorld() {       
        Player player = placePlayer(new Point(1, 1));
        
        checkMovementAndNewPoint(player, Direction.LEFT, new Point(0, 1));        
        checkMovementAndNewPoint(player, Direction.LEFT, new Point(0, 1));        
        checkMovementAndNewPoint(player, Direction.UP, new Point(0, 0));
        checkMovementAndNewPoint(player, Direction.UP, new Point(0, 0));
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(1, 0));        
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(2, 0));        
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(2, 0));        
        checkMovementAndNewPoint(player, Direction.DOWN, new Point(2, 1));
        checkMovementAndNewPoint(player, Direction.DOWN, new Point(2, 2));
        checkMovementAndNewPoint(player, Direction.DOWN, new Point(2, 2));        
    }
    
    @Test
    public void testCanNotMoveToWall1() {             
        Player player = placePlayer(new Point(1, 1));
        placeWall(new Point(0, 1));
        
        checkMovementAndNewPoint(player, Direction.LEFT, new Point(1, 1));        
        checkMovementAndNewPoint(player, Direction.UP, new Point(1, 0));        
        checkMovementAndNewPoint(player, Direction.LEFT, new Point(0, 0));        
        checkMovementAndNewPoint(player, Direction.DOWN, new Point(0, 0)); 
    }
    
    @Test
    public void testCanNotMoveToWall2() {              
        Player player = placePlayer(new Point(1, 1));
        placeWall(new Point(0, 1));         
        placeWall(new Point(2, 1));
        placeWall(new Point(1, 0));        
        placeWall(new Point(1, 2));
        
        checkMovementAndNewPoint(player, Direction.LEFT, new Point(1, 1));        
        checkMovementAndNewPoint(player, Direction.UP, new Point(1, 1));        
        checkMovementAndNewPoint(player, Direction.DOWN, new Point(1, 1));        
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(1, 1)); 
    }
    
    @Test
    public void testPush() {             
        Player player = placePlayer(new Point(0, 1));
        Box box = placeBox(new Point(1, 1));
        
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(1, 1));
        checkWorldObjectIsInPoint(box, new Point(2, 1));
    }

    @Test
    public void testCanNotPushWall() {              
        Player player = placePlayer(new Point(0, 1));
        Wall wall = placeWall(new Point(1, 1));         
              
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(0, 1));        
        checkWorldObjectIsInPoint(wall, new Point(1, 1));
    }
    
    @Test
    public void testCanNotPushOtherPlayer() {              
        Player player = placePlayer(new Point(0, 1));
        Player player2 = placePlayer(new Point(1, 1));        
              
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(0, 1));        
        checkWorldObjectIsInPoint(player2, new Point(1, 1));
    }
    
    @Test
    public void testCanNotPushOutsideWorld() {             
        Player player = placePlayer(new Point(0, 1));
        Box box = placeBox(new Point(1, 1));
        
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(1, 1));
        checkWorldObjectIsInPoint(box, new Point(2, 1));
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(1, 1));
        checkWorldObjectIsInPoint(box, new Point(2, 1));
    }
    
    @Test
    public void testCanNotPushTwoBoxes() {             
        Player player = placePlayer(new Point(0, 1));
        Box box = placeBox(new Point(1, 1));
        Box box2 = placeBox(new Point(2, 1));
        
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(0, 1));
        checkWorldObjectIsInPoint(box, new Point(1, 1));
        checkWorldObjectIsInPoint(box2, new Point(2, 1));
    }
    
    @Test
    public void testCanNotPushToWall() {             
        Player player = placePlayer(new Point(0, 1));
        Box box = placeBox(new Point(1, 1));
        placeWall(new Point(2, 1));
        
        checkMovementAndNewPoint(player, Direction.RIGHT, new Point(0, 1));
        checkWorldObjectIsInPoint(box, new Point(1, 1));
    }
       
}

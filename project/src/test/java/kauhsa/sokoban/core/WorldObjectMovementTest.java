/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.core;
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
    private Player player;
    private WorldMovementHandler worldMovementHandler;
    
    
    @Before
    public void setUp() {
        world = new World(3, 3);        
        fillWorldWithFloor();
        worldMovementHandler = new WorldMovementHandler(world);
        player = new Player();
        world.placeWorldObject(player, new Point(1, 1));        
    }
    
    private void fillWorldWithFloor() {
        for (Point point : world.getPoints()) {
            Floor floor = new Floor();
            world.placeWorldObject(floor, point);
        }
    }
    
    private void placeWall(Point point) {
        Wall wall = new Wall();
        world.placeWorldObject(wall, point);
    }
    
    private void checkPlayerIsInPoint(Point point) {
        boolean isPlayerInPoint = world.getWorldObjectsInPoint(point).contains(player);
        String assertMessage = String.format("Player should now be in %s", point);
        assertTrue(assertMessage, isPlayerInPoint);        
        assertEquals(point, player.getPosition()); // Just to be extra sure
    }
    
    private void checkMovementAndNewPoint(Direction direction, Point point) {
        worldMovementHandler.move(player, direction);
        checkPlayerIsInPoint(point);
    }
    
    @Test
    public void testWorldBounds() {       
        checkMovementAndNewPoint(Direction.LEFT, new Point(0, 1));        
        checkMovementAndNewPoint(Direction.LEFT, new Point(0, 1));        
        checkMovementAndNewPoint(Direction.UP, new Point(0, 0));
        checkMovementAndNewPoint(Direction.UP, new Point(0, 0));
        checkMovementAndNewPoint(Direction.RIGHT, new Point(1, 0));        
        checkMovementAndNewPoint(Direction.RIGHT, new Point(2, 0));        
        checkMovementAndNewPoint(Direction.RIGHT, new Point(2, 0));        
        checkMovementAndNewPoint(Direction.DOWN, new Point(2, 1));
        checkMovementAndNewPoint(Direction.DOWN, new Point(2, 2));
        checkMovementAndNewPoint(Direction.DOWN, new Point(2, 2));        
    }
    
    @Test
    public void testMovingToWall() {
        placeWall(new Point(0, 1));
        
        checkMovementAndNewPoint(Direction.LEFT, new Point(1, 1));        
        checkMovementAndNewPoint(Direction.UP, new Point(1, 0));        
        checkMovementAndNewPoint(Direction.LEFT, new Point(0, 0));        
        checkMovementAndNewPoint(Direction.DOWN, new Point(0, 0)); 
    }
    
    @Test
    public void testMovingIfWallsInEveryDirection() { 
        placeWall(new Point(0, 1));         
        placeWall(new Point(2, 1));
        placeWall(new Point(1, 0));        
        placeWall(new Point(1, 2));
        
        checkMovementAndNewPoint(Direction.LEFT, new Point(1, 1));        
        checkMovementAndNewPoint(Direction.UP, new Point(1, 1));        
        checkMovementAndNewPoint(Direction.DOWN, new Point(1, 1));        
        checkMovementAndNewPoint(Direction.RIGHT, new Point(1, 1)); 
    }    
    
}

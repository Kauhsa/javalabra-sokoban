package kauhsa.sokoban.core;

import kauhsa.sokoban.core.utils.Point;
import kauhsa.sokoban.core.worldobjects.Floor;
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
public class WorldObjectPlacementTest {

    private World world;

    @Before
    public void setUp() {
        world = new World(10, 10);
    }
    
    @Test
    public void testObjectPlacement() {
        assertTrue("World must be empty before anything is placed", world.getWorldObjectsInPoint(new Point(0, 0)).isEmpty());
        
        Floor floor = new Floor();
        world.placeWorldObject(floor, new Point(0, 0));
        
        assertTrue("getWorldObjectsInPoint should contain newly added object", world.getWorldObjectsInPoint(new Point(0, 0)).contains(floor));
        assertEquals("floor's position should be 0, 0 when added to that point in world", new Point(0, 0), floor.getPosition());
    }
    
    @Test
    public void testObjectRelocation() {
        Floor floor = new Floor();
        world.placeWorldObject(floor, new Point(0, 0));
        
        floor.relocate(new Point(1, 0));
        assertTrue("Floor's old position should be empty", world.getWorldObjectsInPoint(new Point(0, 0)).isEmpty());
        assertTrue("New position should contain floor", world.getWorldObjectsInPoint(new Point(1, 0)).contains(floor));
        assertEquals("floor's position should be now 1, 0", new Point(1, 0), floor.getPosition());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testPlacementToNullPoint() {
        Floor floor = new Floor();
        world.placeWorldObject(floor, null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNullObjectPlacement() {
        world.placeWorldObject(null, new Point(0, 0));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testPlacementOutOfBounds() {
        Floor floor = new Floor();
        world.placeWorldObject(floor, new Point(100, 100));
    }
    
    @Test(expected=IllegalStateException.class)
    public void testPlacementOfAlreadyPlacedObject() {
        Floor floor = new Floor();
        world.placeWorldObject(floor, new Point(1, 1));        
        world.placeWorldObject(floor, new Point(1, 2));
    }
    
    @Test(expected=IllegalStateException.class)
    public void testPlacementOfObjectPlacedInOtherWorld() {
        Floor floor = new Floor();
        World world2 = new World(10, 10);
        world.placeWorldObject(floor, new Point(1, 1));
        world2.placeWorldObject(floor, new Point(1, 2));
    }
}

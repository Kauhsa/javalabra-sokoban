package kauhsa.sokoban;

import java.util.Iterator;
import kauhsa.sokoban.core.Point;
import kauhsa.sokoban.core.World;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class WorldTest {
    
    private World world;
    
    @Before
    public void setUp() {
        world = new World(10, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSize1() {
        world = new World(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSize2() {
        world = new World(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSize3() {
        world = new World(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSize4() {
        world = new World(0, 1);
    }
    
    private void widthAndHeightTest(int width, int height) {
        world = new World(width, height);
        assertEquals(width, world.getWidth());        
        assertEquals(height, world.getHeight());
    }
    
    @Test
    public void testGetWidthAndHeight() {
        widthAndHeightTest(10, 10);
        widthAndHeightTest(1, 1);
        widthAndHeightTest(3, 2);
        widthAndHeightTest(1, 6);
    }
    
    private void allValidPointsTest(int width, int height) {
        world = new World(width, height);
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point point = new Point(x, y);
                assertTrue(String.format("Point %s was not valid in %dx%d world", point, width, height), world.isPointInWorld(point));
            }
        }
    }
    
    @Test
    public void testValidPointsInWorld() {
        allValidPointsTest(1, 1);
        allValidPointsTest(100, 100);        
        allValidPointsTest(48, 100);        
        allValidPointsTest(100, 48);
    }
    
    @Test
    public void testInvalidPointsInWorld() {
        assertFalse(world.isPointInWorld(new Point(10, 10)));
        assertFalse(world.isPointInWorld(new Point(0, 10)));
        assertFalse(world.isPointInWorld(new Point(10, 0)));
        assertFalse(world.isPointInWorld(new Point(15, 15)));
        assertFalse(world.isPointInWorld(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE)));
    }
    
    @Test
    public void testWorldPointIterator() {
        Iterator<Point> iterator = world.getPointIterator();
        
        while (iterator.hasNext()) {
            Point currentPoint = iterator.next();
            assertTrue(world.isPointInWorld(currentPoint));
        }
    }

    
    
}
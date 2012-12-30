package kauhsa.sokoban.core;

import kauhsa.sokoban.core.utils.Point;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mika
 */
public class WorldPointTest {

    private World world;
    
    @Before
    public void setUp() {
        world = new World(10, 10);
    }
    
    @Test
    public void testWorldPointIterator() {
        for (Point point : world.getPoints()) {
            assertTrue(world.isPointInWorld(point));
        }
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
        assertFalse(world.isPointInWorld(new Point(10, 0)));
        assertFalse(world.isPointInWorld(new Point(0, 10)));
        assertFalse(world.isPointInWorld(new Point(15, 15)));
        assertFalse(world.isPointInWorld(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE)));
    }
}

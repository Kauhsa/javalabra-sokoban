package kauhsa.sokoban.core;

import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class WorldInitalizationTest {

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

    @Test
    public void testNoAnyWorldObjectsAnAnyPointAtInit() {
        for (Point point : world.getPoints()) {
            Collection<WorldObject> worldObjectsAtCurrentPoint = world.getWorldObjectsInPoint(point);
            assertTrue(worldObjectsAtCurrentPoint.isEmpty());
        }
    }

    @Test
    public void testNoWorldObjectsOfAnyTypeAtInit() {
        for (WorldObjectType type : WorldObjectType.values()) {
            Collection<WorldObject> worldObjectsOfCurrentType = world.getWorldObjectsOfType(type);
            assertTrue(worldObjectsOfCurrentType.isEmpty());
        }
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
}
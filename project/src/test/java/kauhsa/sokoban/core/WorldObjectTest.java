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
 * Because WorldObject is abstract class, this test uses the most basic
 * WorldObject, which is Floor.
 *
 * @author mika
 */
public class WorldObjectTest {

    private Floor floor;
    private World world;

    @Before
    public void setUp() {
        floor = new Floor();
        world = new World(10, 10);
    }

    @Test
    public void testPositionAtInitalization() {
        assertNull(floor.getPosition());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSettingNullWorld() {
        floor.setWorld(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSettingNullPosition() {
        floor.setWorld(world);
        floor.setPosition(null);
    }
    
    @Test(expected=IllegalStateException.class)
    public void testSettingPositionBeforeWorld() {
        floor.setPosition(new Point(1, 1));
    }
}

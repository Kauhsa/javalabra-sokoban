/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.core;

import kauhsa.sokoban.core.utils.Point;
import kauhsa.sokoban.core.worldobjects.Box;
import kauhsa.sokoban.core.worldobjects.BoxTarget;
import kauhsa.sokoban.core.worldobjects.Floor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mika
 */
public class WorldStatusTest {
    private World world;
    private Box box;
    private WorldStatus ws;
    
    @Before
    public void setUp() {
        world = new World(3, 1);
        world.placeWorldObject(new Floor(), new Point(0, 0));        
        world.placeWorldObject(new Floor(), new Point(1, 0));        
        world.placeWorldObject(new Floor(), new Point(2, 0));        
        world.placeWorldObject(new BoxTarget(), new Point(2, 0));
        
        box = new Box();
        world.placeWorldObject(box, new Point(1, 0));        
        
        ws = new WorldStatus(world);
    }
    
    @Test
    public void testWorldNotFinishedYet() {
        assertFalse(ws.isFinished());
    }
    
    @Test
    public void testWorldFinished() {
        box.relocate(new Point(2, 0));
        assertTrue(ws.isFinished());
    }
}

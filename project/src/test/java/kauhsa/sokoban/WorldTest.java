package kauhsa.sokoban;

import kauhsa.sokoban.core.World;
import static org.junit.Assert.*;
import org.junit.Test;

public class WorldTest {

    @Test
    public void testCreateWithProperSize() {
        World world = new World(3, 3);
        world = new World(1, 1);
        world = new World(1000, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSize1() {
        World world = new World(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSize2() {
        World world = new World(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSize3() {
        World world = new World(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSize4() {
        World world = new World(0, 1);
    }
}
package kauhsa.sokoban.core;

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
public class PointTest {
    
    private Point point;
    
    @Before
    public void setUp() {
        point = new Point(0, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void testInvalidPosition1() {
        new Point(-1, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void testInvalidPosition2() {
        new Point(0, -1);
    }
    
    public void testPointExists() {
        // TODO: Is tested better elsewhere - move those test maybe here instead
        assertTrue(point.pointExistInArray(1, 1));
        assertFalse(point.pointExistInArray(0, 0));
    }
    
}

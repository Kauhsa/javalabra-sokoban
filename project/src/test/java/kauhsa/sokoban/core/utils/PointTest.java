package kauhsa.sokoban.core.utils;

import kauhsa.sokoban.core.utils.Point;
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
    
    @Test
    public void testEquivalence() {
        assertEquals(new Point(0, 0), new Point(0, 0));        
        assertEquals(new Point(1, 1), new Point(1, 1));   
        assertNotEquals(new Point(0, 0), new Point(0, 1));         
        assertNotEquals(new Point(1, 0), new Point(0, 0));                 
        assertNotEquals(new Point(1, 0), null);
        assertNotEquals(new Point(1, 0), "GREAT SYSTEMS COLLIDE");
    }
    
    private void testPointIsEqualToCopy(Point point) {
        assertEquals(point, point.copy());
    }
    
    @Test
    public void testCopy() {
        testPointIsEqualToCopy(new Point(0, 0));
        testPointIsEqualToCopy(new Point(3, 0));
        testPointIsEqualToCopy(new Point(0, 3));
    }
    
    @Test
    public void testPointExists() {
        // TODO: Is tested better elsewhere - move those test maybe here instead
        assertTrue(point.pointExistInArray(1, 1));
        assertFalse(point.pointExistInArray(0, 0));
    }
    
}

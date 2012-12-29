package kauhsa.sokoban.core;

import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mika
 */
public class PointIteratorTest {
       
    @Test(expected=IllegalArgumentException.class)    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void testIllegalSize1() {
        new PointIterator(-1, 0);
    }
        
    @Test(expected=IllegalArgumentException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void testIllegalSize2() {
        new PointIterator(1, -1);
    }
    
    /**
     * Return true if there are only true values in 2-dimensional boolean array.
     */
    private boolean areAllTrue(boolean[][] array) {
        for (boolean[] row : array) {
            for (boolean cell : row) {
                if (!cell) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Test if iterator:
     * 1) Returns every Point object at least once
     * 2) Returns correct amount of Points total
     */
    private void everyPointExactlyOnceTest(int width, int height) {
        boolean[][] points = new boolean[width][height];
        int pointCount = 0;
        
        for (Point point : new PointIterator(width, height)) {
            assertTrue(String.format("Point %s is not inside %dx%d array", point, width, height), point.pointExistInArray(width, height));
            points[point.getX()][point.getY()] = true;
            pointCount++;
        }
        
        assertEquals("There should be width * height points total", width * height, pointCount);
        assertTrue("All points should be visited at least once", areAllTrue(points));
    }
    
    @Test
    public void testEveryPointIsReturnedExactlyOnce() {
        everyPointExactlyOnceTest(1, 5);
        everyPointExactlyOnceTest(1, 1);
        everyPointExactlyOnceTest(10, 50);
        everyPointExactlyOnceTest(40, 23);        
        everyPointExactlyOnceTest(21, 62);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testUnsupportedRemoval() {
        PointIterator pointIterator = new PointIterator(10, 10);
        pointIterator.remove();
    }
    
    @Test(expected=NoSuchElementException.class)
    public void testIteratorDoesNotLoop() {
        PointIterator pointIterator = new PointIterator(10, 10);
        while (true) {
            pointIterator.next();
        }
    }
}

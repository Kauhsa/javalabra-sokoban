package kauhsa.sokoban;

import kauhsa.sokoban.core.Point;
import kauhsa.sokoban.core.PointIterator;
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
public class PointIteratorTest {
    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 5;
    
    private PointIterator pointIterator;
   
    @Before
    public void setUp() {
        pointIterator = new PointIterator(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalSize1() {
        pointIterator = new PointIterator(-1, 0);
    }
        
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalSize2() {
        pointIterator = new PointIterator(1, -1);
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
        pointIterator = new PointIterator(width, height);
        boolean[][] points = new boolean[width][height];
        int pointCount = 0;
        
        while (pointIterator.hasNext()) {
            Point currentPoint = pointIterator.next();
            assertTrue(String.format("Point %s is not inside %dx%d array", currentPoint, width, height), currentPoint.pointExistInArray(width, height));
            points[currentPoint.getX()][currentPoint.getY()] = true;
            pointCount++;
        }
        
        assertEquals("There should be width * height points total", width * height, pointCount);
        assertTrue("All points should be visited at least once", areAllTrue(points));
    }
    
    @Test
    public void testEveryPointIsReturnedExactlyOnce() {
        everyPointExactlyOnceTest(1, 5);
        everyPointExactlyOnceTest(1, 1);
        everyPointExactlyOnceTest(1000, 1000);
        everyPointExactlyOnceTest(400, 2341);        
        everyPointExactlyOnceTest(2341, 400);
    }
}

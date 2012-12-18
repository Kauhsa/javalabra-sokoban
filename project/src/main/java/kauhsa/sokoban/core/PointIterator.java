package kauhsa.sokoban.core;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for iterating every Point object in grid exactly once.
 * 
 * If height and width is set to 2, the result would be:
 * 
 * (0, 0)
 * (0, 1)
 * (1, 0)
 * (1, 1)
 * 
 * Order of points is not defined.
 * 
 * @author mika
 */
public class PointIterator implements Iterable<Point>, Iterator<Point> {
    private int currentX = 0;
    private int currentY = 0;
    private final int width;
    private final int height;
    private boolean hasNext = true;
    
    /**
     * Create new PointIterator.
     * 
     * Width or height can't be negative.
     * 
     * @param width width of grid
     * @param height height of grid
     */
    public PointIterator(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Width or height cannot be negative");
        }
        
        this.width = width;
        this.height = height;
    }
    
    public boolean hasNext() {
        return hasNext;
    }
    
    private Point pointFromCurrentPosition() {
        return new Point(currentX, currentY);
    }
    
    private void advance() {
        if (currentX >= width - 1) {
            currentX = 0;
            currentY++;
        } else {
            currentX++;
        }
        
        if (currentY >= height) {
            hasNext = false;
        }
    }
    
    public Point next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        
        Point point = pointFromCurrentPosition();
        advance();
        return point;
    }

    public void remove() {
        throw new UnsupportedOperationException("Removal is not supported");
    }

    public Iterator<Point> iterator() {
        return new PointIterator(width, height);
    }
    
}

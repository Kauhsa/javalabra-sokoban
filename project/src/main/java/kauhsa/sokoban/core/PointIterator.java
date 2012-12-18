package kauhsa.sokoban.core;

import java.util.Iterator;

/**
 *
 * @author mika
 */
public class PointIterator implements Iterator<Point> {
    private int currentX = 0;
    private int currentY = 0;
    private final int width;
    private final int height;
    
    public PointIterator(int width, int height) {
        this.width = height;
        this.height = height;
    }
    
    public boolean hasNext() {
        if (currentX >= width - 1|| currentY >= height - 1) {
            return false;
        }
        
        return true;
    }
    
    private Point pointFromCurrentPosition() {
        return new Point(currentX, currentY);
    }
    
    private void advance() {
        currentX++;
        if (currentX == height) {
            currentX = 0;
            currentY++;
        }
    }
    
    public Point next() {
        Point point = pointFromCurrentPosition();
        advance();
        return point;
    }

    public void remove() {
        throw new UnsupportedOperationException("Removal is not supported");
    }
    
}

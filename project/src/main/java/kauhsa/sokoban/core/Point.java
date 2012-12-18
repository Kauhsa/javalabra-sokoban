package kauhsa.sokoban.core;

/**
 * Single point in the world.
 * 
 * @author mika
 */
public class Point {
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Point cannot have negative x or y");
        }
        
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}

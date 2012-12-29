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
        if (wouldBeInvalid(x, y)) {
            throw new IllegalArgumentException("Point cannot have negative x or y");
        }

        this.x = x;
        this.y = y;
    }

    public static boolean wouldBeInvalid(int x, int y) {
        return (x < 0 || y < 0);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean pointExistInArray(int width, int height) {
        /*
         * No need to check for negative coordinates, as Point object can't have
         * them in the first place.
         */
        if (this.x > width - 1 || this.y > height - 1) {
            return false;
        }

        return true;
    }

    public Point copy() {
        return new Point(this.x, this.y);
    }
    
    public Point applyDirection(Direction direction) {
        int x = this.getX();
        int y = this.getY();
        
        if (direction == Direction.DOWN) {
            y++;
        } else if (direction == Direction.UP) {
            y--;
        } else if (direction == Direction.LEFT) {
            x--;
        } else if (direction == Direction.RIGHT) {
            x++;
        }
        
        return new Point(x, y);
    }
    
    public boolean appliedDirectionWouldBeInvalid(Direction direction) {
        try {
            this.applyDirection(direction);
        } catch (IllegalArgumentException e) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
}

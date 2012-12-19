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

package kauhsa.sokoban.core.utils;

/**
 * Class that represents a single coordinate in two-dimensional grid - often in
 * {@link World}.
 */
public class Point {

    private final int x;
    private final int y;

    /**
     * Create a new Point.
     * 
     * @param x horizontal coordinate of new Point.
     * @param y vertical coordinate of new Point.
     * @throws IllegalArgumentException if x or y is negative.
     */
    public Point(int x, int y) {
        if (wouldBeInvalid(x, y)) {
            throw new IllegalArgumentException("Point cannot have negative x or y");
        }

        this.x = x;
        this.y = y;
    }

    /**
     * Return if Point created with this x and y coordinate would be invalid or
     * not.
     * 
     * In other words, is x or y negative.
     * 
     * @param x x coordinate of imaginary Point.
     * @param y y coordinate of imaginary Point.
     * @return true if Point would be invalid, otherwise false.
     */
    public static boolean wouldBeInvalid(int x, int y) {
        return (x < 0 || y < 0);
    }

    /**
     * Get x coordinate of this Point.
     * 
     * @return x coordinate of this Point.
     */
    public int getX() {
        return x;
    }

    /**
     * Get y coordinate of this Point.
     * 
     * @return y coordinate of this Point.
     */
    public int getY() {
        return y;
    }

    /**
     * If we had an imaginary grid with defined width and height, return if this
     * Point would be inside of it.
     * 
     * @param width width of the imaginary grid.
     * @param height height of the imaginary grid.
     * @return true if this Point would be inside, otherwise false.
     */
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
    
    /**
     * Return a new Point an one unit away from this Point.
     * 
     * @param direction direction we want to apply.
     * @return new Point an one unit away in defined direction.
     * @throws IllegalArgumentException if new Point would be invalid - in other
     * words, it's x or y would be invalid.
     */
    public Point applyDirection(Direction direction) {
        int newX = this.getX();
        int newY = this.getY();

        if (direction == Direction.DOWN) {
            newY++;
        } else if (direction == Direction.UP) {
            newY--;
        } else if (direction == Direction.LEFT) {
            newX--;
        } else if (direction == Direction.RIGHT) {
            newX++;
        }

        return new Point(newX, newY);
    }

    /**
     * Return if a new Point an one unit away from this Point would be invalid.
     *
     * @param direction direction we want to apply.
     * @return true if the Point would be invalid, otherwise false.
     * @see applyDirection
     */
    public boolean wouldAppliedDirectionBeInvalid(Direction direction) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.x;
        hash = 73 * hash + this.y;
        return hash;
    }
}

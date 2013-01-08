package kauhsa.sokoban.ui.elements.world;

/**
 * Class that calculates tile scale and positions based on size of world and
 * rendering canvas and the properties of a TileSet.
 *
 * This class will attempt to use all the space available while fitting the
 * whole world to the rendering canvas - it will not stretch the tiles beyond
 * their original size though. The world is centered vertically and
 * horizontally.
 */
public class TilePositionManager {

    private final float tileWidth;
    private final float tileHeight;
    private final float canvasWidth;
    private final float canvasHeight;
    private final float tileHorizontalMovement;
    private final float tileVerticalMovement;
    private final int worldWidth;
    private final int worldHeight;
    private float scale;
    private float horizontalRenderPosition;
    private float verticalRenderPosition;

    /**
     * Create a new TilePositionManager.
     *
     * @param tileSet TileSet that will be rendered with the help of this class.
     * @param canvasWidth Width of the rendering canvas.
     * @param canvasHeight Height of the rendering canvas.
     * @param worldWidth Width of the world in tiles.
     * @param worldHeight Height of the world in tiles.
     */
    public TilePositionManager(TileSet tileSet, float canvasWidth, float canvasHeight, int worldWidth, int worldHeight) {
        this.tileWidth = tileSet.getTileWidth();
        this.tileHeight = tileSet.getTileHeight();
        this.tileHorizontalMovement = tileSet.getTileHorizontalMovement();
        this.tileVerticalMovement = tileSet.getTileVerticalMovement();
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;

        this.calculate();
    }

    /**
     * Calculate scale and some offsets using the information given on
     * constructor.
     */
    private void calculate() {
        float worldPixelWidth = (worldWidth - 1) * tileHorizontalMovement + tileWidth;
        float worldPixelHeight = (worldHeight - 1) * tileVerticalMovement + tileHeight;

        float scaleX = canvasWidth / worldPixelWidth;
        float scaleY = canvasHeight / worldPixelHeight;

        if (scaleX > 1 && scaleY > 1) {
            scale = 1;
            horizontalRenderPosition = canvasWidth / 2 - worldPixelWidth / 2;
            verticalRenderPosition = canvasHeight / 2 - worldPixelHeight / 2;
        } else if (scaleX < scaleY) {
            scale = scaleX;
            horizontalRenderPosition = 0;
            verticalRenderPosition = canvasHeight / 2 - worldPixelHeight / 2 * scale;
        } else {
            scale = scaleY;
            horizontalRenderPosition = canvasWidth / 2 - worldPixelWidth / 2 * scale;
            verticalRenderPosition = 0;
        }
    }

    /**
     * Get the scale tiles should be rendered in.
     *
     * This means the height (and width) of rendered tile should be normal
     * height of tile * scale.
     *
     * @return float between 0 and 1.
     */
    public float getScale() {
        return scale;
    }

    /**
     * Get the horizontal position of where tile should be rendered.
     *
     * @param x horizontal position of the tile in world.
     * @return horizontal position of the tile in canvas.
     */
    public float getHorizontalTilePosition(int x) {
        return (float) Math.floor(horizontalRenderPosition) + (x * tileHorizontalMovement * scale);
    }

    /**
     * Get the vertical position of where tile should be rendered.
     *
     * @param y vertical position of the tile in world.
     * @return vertical position of the tile in canvas.
     */
    public float getVerticalTilePosition(int y) {
        return (float) Math.floor(verticalRenderPosition) + (y * tileVerticalMovement * scale);
    }
}

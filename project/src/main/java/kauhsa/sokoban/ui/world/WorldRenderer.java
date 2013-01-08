package kauhsa.sokoban.ui.world;

import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.utils.Point;
import kauhsa.sokoban.ui.elements.UIElement;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class for rendering a world.
 */
public class WorldRenderer extends UIElement {    
    private final World world;
    private final WorldPointImages worldPointImages;
    private final TileSet tileSet;
    
    /**
     * Create a new WorldRenderer.
     * 
     * @param world world that will be rendered.
     * @param tileSet tileSet that will be used for rendering the world.
     */
    public WorldRenderer(World world, TileSet tileSet) throws SlickException {
        this.world = world;        
        this.worldPointImages = new WorldPointImages(world, tileSet);
        this.tileSet = tileSet;
    }
    
    private TilePositionManager initializeTilePositionManager(float width, float height) {
        return new TilePositionManager(tileSet, width, height, this.world.getWidth(), this.world.getHeight());
    }
    
    /**
     * Render a world to specific location.
     * 
     * @param x horizontal position of rendered world.
     * @param y vertical position of rendered world.
     * @param width width of rendered world.
     * @param height height of rendered world.
     */
    public void render(Graphics graphics, float x, float y, float width, float height) {
        TilePositionManager tilePositionManager = initializeTilePositionManager(width, height);        
        float scale = tilePositionManager.getScale();      
        
        for (Point point : world.getPoints()) {
            float tileX = tilePositionManager.getHorizontalTilePosition(point.getX()) + x;
            float tileY = tilePositionManager.getVerticalTilePosition(point.getY()) + y;
                        
            for (Image image : worldPointImages.imagesInPoint(point)) {
                image.draw(tileX, tileY, scale);
            }
        }        
    }
}

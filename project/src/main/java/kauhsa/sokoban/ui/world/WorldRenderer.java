/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.world;

import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.utils.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author mika
 */
public class WorldRenderer {
    private final float TILE_WIDTH = 101;
    private final float TILE_HEIGHT = 211;
    
    private final float TILE_HORIZONTAL_MOVE = 100;
    private final float TILE_VERTICAL_MOVE = 80;
    
    private final World world;
    private final WorldPointImages worldPointImages;
    
    public WorldRenderer(World world) {
        this.world = world;        
        this.worldPointImages = new WorldPointImages(world);
    }
    
    private TilePositionManager initializeTilePositionManager(float x, float y, float width, float height) {
        return new TilePositionManager(TILE_WIDTH, TILE_HEIGHT, TILE_HORIZONTAL_MOVE, TILE_VERTICAL_MOVE, width, height, this.world.getWidth(), this.world.getHeight());
    }
    
    public void render(float x, float y, float width, float height) {
        TilePositionManager tilePositionManager = initializeTilePositionManager(x, y, width, height);        
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

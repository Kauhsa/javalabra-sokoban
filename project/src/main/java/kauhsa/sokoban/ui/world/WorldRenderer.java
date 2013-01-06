/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.world;

import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.utils.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mika
 */
public class WorldRenderer {
    
    private final World world;
    private final WorldPointImages worldPointImages;
    private final TileSet tileSet;
    
    public WorldRenderer(World world, TileSet tileSet) throws SlickException {
        this.world = world;        
        this.worldPointImages = new WorldPointImages(world);
        this.tileSet = tileSet;
    }
    
    private TilePositionManager initializeTilePositionManager(float x, float y, float width, float height) {
        return new TilePositionManager(tileSet, width, height, this.world.getWidth(), this.world.getHeight());
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

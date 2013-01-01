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
    private final int TILE_WIDTH = 101;
    private final int TILE_HEIGHT = 211;
    
    private final int TILE_X_MOVE = 100;
    private final int TILE_Y_MOVE = 80;
    
    private final World world;
    private final WorldPointImages worldPointImages;
    
    private float canvasX1;    
    private float canvasY1;
    private float canvasX2;
    private float canvasY2;
    
    private float renderX;
    private float renderY;
    private float scale;
    
    public WorldRenderer(World world) {
        this.world = world;        
        this.worldPointImages = new WorldPointImages(world);
    }
    
    private float worldXToScreenX(int x) {
        return x * TILE_X_MOVE * scale + renderX + canvasX1;
    }
    
    private float worldYToScreenY(int y) {
        return y * TILE_Y_MOVE * scale + renderY + canvasY1;
    }
    
    private void calculateDrawingPositions(float x1, float y1, float x2, float y2) {
        canvasX1 = x1;
        canvasY1 = y1;
        canvasX2 = x2;
        canvasY2 = y2;
        
        float canvasWidth = canvasX2 - canvasX1;
        float canvasHeight = canvasY2 - canvasY1;
        
        int worldWidth = (world.getWidth() - 1) * TILE_X_MOVE + TILE_WIDTH;
        int worldHeight = (world.getHeight() - 1) * TILE_Y_MOVE + TILE_HEIGHT;
        
        float scaleX = canvasWidth / worldWidth;
        float scaleY = canvasHeight / worldHeight;
        
        if (scaleX > 1 && scaleY > 1)  {
            scale = 1;
            renderX = canvasWidth / 2 - worldWidth / 2;
            renderY = canvasHeight / 2 - worldHeight / 2;
        } else if (scaleX < scaleY) {
            scale = scaleX;
            renderX = 0;
            renderY = canvasHeight / 2 - worldHeight / 2 * scale;
        } else {
            scale = scaleY;
            renderX = canvasWidth / 2 - worldWidth / 2 * scale;
            renderY = 0;
        }
    }
    
    public void render(float x1, float y1, float x2, float y2) {
        if (x1 != canvasX1 || x2 != canvasX2 || y1 != canvasY1 || y2 != canvasY2) {
            calculateDrawingPositions(x1, y1, x2, y2);
        }
        
        for (Point point : world.getPoints()) {
            float x = worldXToScreenX(point.getX());
            float y = worldYToScreenY(point.getY());
                        
            for (Image image : worldPointImages.imagesInPoint(point)) {
                image.draw(x, y, scale);
            }
        }        
    }

}

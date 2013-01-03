/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.world;

/**
 *
 * @author mika
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

    public TilePositionManager(float tileWidth, float tileHeight, float tileHorizontalMovement, float tileVerticalMovement, float canvasWidth, float canvasHeight, int worldWidth, int worldHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileHorizontalMovement = tileHorizontalMovement;        
        this.tileVerticalMovement = tileVerticalMovement;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        
        this.calculate();
    }

    private void calculate() {        
        float worldPixelWidth = (worldWidth - 1) * tileHorizontalMovement + tileWidth;
        float worldPixelHeight = (worldHeight - 1) * tileVerticalMovement + tileHeight;
        
        float scaleX = canvasWidth / worldPixelWidth;
        float scaleY = canvasHeight / worldPixelHeight;
        
        if (scaleX > 1 && scaleY > 1)  {
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
    
    public float getScale() {
        return scale;
    }
    
    public float getHorizontalTilePosition(int x) {
        return (float) (Math.floor(horizontalRenderPosition) + (x * tileHorizontalMovement * scale));
    }
    
    public float getVerticalTilePosition(int y) {
        return (float) Math.floor(verticalRenderPosition) + (y * tileVerticalMovement * scale);
    }
}

package kauhsa.sokoban.ui;

import java.util.HashMap;
import java.util.Map;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;
import kauhsa.sokoban.ui.world.TileSet;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * TileSet that implements tiles found from here:
 * http://www.lostgarden.com/2007/05/cutegod-prototyping-challenge.html
 */
public class CuteTileSet implements TileSet {    
    private Map<String, Image> imageCache = new HashMap<String, Image>();    
    
    private final float TILE_WIDTH = 101;
    private final float TILE_HEIGHT = 211;    
    private final float TILE_HORIZONTAL_MOVE = 100;
    private final float TILE_VERTICAL_MOVE = 80;
    
    public CuteTileSet() throws SlickException {        
        imageCache.put("FLOOR", new Image("tiles/Grass Block Resized.png"));
        imageCache.put("PLAYER", new Image("tiles/Character Princess Girl.png"));
        imageCache.put("WALL", new Image("tiles/Stone Block Tall Resized.png"));
        imageCache.put("BOX", new Image("tiles/Rock.png"));        
        imageCache.put("BOXTARGET", new Image("tiles/Selector.png"));
    }
    
    @Override
    public Image getImageForWorldObject(WorldObject worldObject) {
        WorldObjectType worldObjectType = worldObject.getType();
        if (worldObjectType == WorldObjectType.WALL) {
            return imageCache.get("WALL");
        } else if (worldObjectType == WorldObjectType.PLAYER) {
            return imageCache.get("PLAYER");
        } else if (worldObjectType == WorldObjectType.FLOOR) {
            return imageCache.get("FLOOR");
        } else if (worldObjectType == WorldObjectType.BOX) {
            return imageCache.get("BOX");
        } else if (worldObjectType == WorldObjectType.BOXTARGET) {
            return imageCache.get("BOXTARGET");
        } else {
            return imageCache.get("PLAYER");
        }
    }

    public float getTileWidth() {
        return TILE_WIDTH;
    }

    public float getTileHeight() {
        return TILE_HEIGHT;
    }

    public float getTileHorizontalMovement() {
        return TILE_HORIZONTAL_MOVE;
    }

    public float getTileVerticalMovement() {
        return TILE_VERTICAL_MOVE;
    }
}

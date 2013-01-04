/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.world;

import java.util.HashMap;
import java.util.Map;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mika
 */
public class WorldObjectImage {    
    private Map<String, Image> imageCache = new HashMap<String, Image>();
    
    public WorldObjectImage() throws SlickException {        
        imageCache.put("FLOOR", new Image("tiles/Grass Block Resized.png"));
        imageCache.put("PLAYER", new Image("tiles/Character Princess Girl.png"));
        imageCache.put("WALL", new Image("tiles/Stone Block Tall Resized.png"));
        imageCache.put("BOX", new Image("tiles/Rock.png"));        
        imageCache.put("BOXTARGET", new Image("tiles/Selector.png"));
    }
    
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
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.world;

import java.io.InputStream;
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
    private final String imageResourcePath;
    
    public WorldObjectImage(String imageResourcePath) {
        this.imageResourcePath = imageResourcePath;
        
        imageCache.put("FLOOR", resourceToImage("Grass Block Resized.png"));
        imageCache.put("PLAYER", resourceToImage("Character Princess Girl.png"));
        imageCache.put("WALL", resourceToImage("Stone Block Tall Resized.png"));
        imageCache.put("BOX", resourceToImage("Rock.png"));        
        imageCache.put("BOXTARGET", resourceToImage("Selector.png"));
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
            
    private Image resourceToImage(String imageName) {
        String resourceLocation = imageResourcePath + "/" + imageName;
        InputStream resource = WorldObjectImage.class.getResourceAsStream(resourceLocation);
        
        try {
            return new Image(resource, imageName, false);
        } catch (SlickException ex) {
            throw new RuntimeException(String.format("Error loading image from resource \"%s\"", imageName));
        }
    }
}

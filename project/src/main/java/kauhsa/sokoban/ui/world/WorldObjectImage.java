/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.world;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import kauhsa.sokoban.core.WorldObject;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mika
 */
public class WorldObjectImage {
    private Map<String, Image> imageCache = new HashMap<String, Image>();
    
    public WorldObjectImage() {
        imageCache.put("wall", resourceToImage("cobble_blood1.png"));
    }
    
    public Image getImageForWorldObject(WorldObject worldObject) {
        return imageCache.get("wall");
    }
            
    private Image resourceToImage(String location) {
        InputStream resource = WorldObjectImage.class.getResourceAsStream(location);
        
        try {
            return new Image(resource, location, true);
        } catch (SlickException ex) {
            Logger.getLogger(WorldObjectImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.world;

import kauhsa.sokoban.ui.CuteTileSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.utils.Point;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mika
 */
public class WorldPointImages {
    private final World world;
    private final CuteTileSet worldObjectImage;
    
    public WorldPointImages(World world) throws SlickException {
        this.world = world;
        this.worldObjectImage = new CuteTileSet();        
    }
    
    public Image[] imagesInPoint(Point point) {
        Set<WorldObject> worldObjectList = world.getWorldObjectsInPoint(point);
        WorldObject[] worldObjects = new WorldObject[worldObjectList.size()];
        worldObjects = world.getWorldObjectsInPoint(point).toArray(worldObjects);
        
        Arrays.sort(worldObjects, new WorldObjectOrderComparator());

        Image[] images = new Image[worldObjects.length];
        for (int i = 0; i < images.length; i++) {
            images[i] = worldObjectImage.getImageForWorldObject(worldObjects[i]);
        }
        
        return images;
    }    
}

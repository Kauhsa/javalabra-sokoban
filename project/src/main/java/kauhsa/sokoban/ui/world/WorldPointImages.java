package kauhsa.sokoban.ui.world;

import java.util.Arrays;
import java.util.Set;
import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.utils.Point;
import kauhsa.sokoban.ui.CuteTileSet;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class for returning the list of Images that represent a single point in
 * world.
 */
public class WorldPointImages {
    private final World world;
    private final TileSet worldObjectImage;
    
    /**
     * Create new WorldPointImages object.
     * 
     * @param world world rendered.
     */
    public WorldPointImages(World world) throws SlickException {
        this.world = world;
        this.worldObjectImage = new CuteTileSet();        
    }
    
    /**
     * Get the array of Images that represent a single point in world.
     * 
     * The images are in the order they should be rendered in.
     * 
     * @param point position which Images are returned.
     * @return array of Images. 
     */
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

package kauhsa.sokoban.ui.world;

import java.util.Comparator;
import kauhsa.sokoban.core.WorldObject;
import org.newdawn.slick.Image;

/**
 * Interface for classes that return a Slick Image for a WorldObject.
 */
public interface TileSet {

    /**
     * Get Image for WorldObject.
     * 
     * This Image is an graphical representation of a specific WorldObject.
     *
     * @param worldObject WorldObject whose graphical representation we want.
     * @return Image object for the WorldObject given as parameter.
     */
    public Image getImageForWorldObject(WorldObject worldObject);

    /**
     * Get the width of single tile.
     * 
     * @return width of single tile.
     */
    public float getTileWidth();
    
    /**
     * Get the height of single tile.
     * 
     * @return height of single tile.
     */
    public float getTileHeight();

    /**
     * Get the difference in horizontal axis between tiles that are next to each
     * other horizontally.
     * 
     * This should be different than getTileWidth if the tiles are supposed to
     * overlap each other.
     * 
     * @return the difference in horizontal axis between two tiles.
     */
    public float getTileHorizontalMovement();

    /**
     * Get the difference in vertical axis between tiles that are next to each
     * other vertically.
     * 
     * This should be different than getTileHeight if the tiles are supposed to
     * overlap each other.
     * 
     * @return the difference in vertical axis between two tiles.
     */
    public float getTileVerticalMovement();
    
    /**
     * Get Comparator that defines the rendering order of tiles.
     * 
     * @return Comparator of WorldObjects.
     */
    public Comparator<WorldObject> getRenderOrderComparator();
}

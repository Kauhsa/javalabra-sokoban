/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.world;

import kauhsa.sokoban.core.WorldObject;
import org.newdawn.slick.Image;

/**
 *
 * @author mika
 */
public interface TileSet {
    public Image getImageForWorldObject(WorldObject worldObject);    
    public float getTileWidth();
    public float getTileHeight();
    public float getTileHorizontalMovement();
    public float getTileVerticalMovement();    
}

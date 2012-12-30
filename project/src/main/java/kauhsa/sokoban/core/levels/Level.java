
package kauhsa.sokoban.core.levels;

import kauhsa.sokoban.core.World;

/**
 *
 * @author mika
 */
public interface Level {
    
    /**
     * 
     * @return 
     */
    public World getWorld();
    
    /**
     * 
     * @return 
     */
    public String getName();
    
    /**
     * 
     * @return 
     */
    public String getAuthor();
    
}

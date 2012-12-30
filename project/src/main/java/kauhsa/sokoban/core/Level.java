
package kauhsa.sokoban.core;

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

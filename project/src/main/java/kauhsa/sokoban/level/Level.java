
package kauhsa.sokoban.level;

import kauhsa.sokoban.core.World;
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
    public World getWorld() throws InvalidLevelException;
    
    /**
     * 
     * @return 
     */
    public String getName() throws InvalidLevelException;
    
    /**
     * 
     * @return 
     */
    public String getAuthor() throws InvalidLevelException;
    
}

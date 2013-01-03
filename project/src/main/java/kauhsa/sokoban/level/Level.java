
package kauhsa.sokoban.level;

import kauhsa.sokoban.core.World;

/**
 * Interface for Level, which is an container for metadata and also is a
 * "World-factory" of sorts.
 */
public interface Level {
    
    /**
     * Generate World.
     * 
     * This method should always create an fresh copy of World.
     *
     * @return generated World.
     * @throws InvalidLevelException
     */
    public World generateWorld() throws InvalidLevelException;
    
    
    /**
     * Get metadata.
     * 
     * Metadata means things like "name" and "author" - Level is
     * not required to supply any of this, though. Should return null if
     * metadata for given key is not found.
     * 
     * @param key key of metadata
     * @return metadata.
     */
    public String getMetadata(String key);
    
}

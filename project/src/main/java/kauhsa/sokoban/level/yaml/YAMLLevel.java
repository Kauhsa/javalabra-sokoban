package kauhsa.sokoban.level.yaml;

import java.io.InputStream;
import kauhsa.sokoban.core.World;
import kauhsa.sokoban.level.Level;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

/**
 * Class for loading Level data that is in YAML format.
 */
public class YAMLLevel implements Level {
    
    private final YAMLLevelData fileLevelData;
    
    /**
     * Create new YAMLLevel.
     * 
     * @param inputStream YAML-formatted data.
     */
    public YAMLLevel(InputStream inputStream) throws InvalidYAMLLevelException {        
        Yaml yaml = new Yaml();
        try {
            fileLevelData = yaml.loadAs(inputStream, YAMLLevelData.class);
        } catch (YAMLException e) {
            throw new InvalidYAMLLevelException("Invalid YAML data");
        }
        
        if (fileLevelData.getWorld() == null) {
            throw new InvalidYAMLLevelException("No world data in level");
        }
    }

    public World generateWorld() throws InvalidYAMLLevelException {
        return YAMLLevelWorldCreator.createWorld(fileLevelData.getWorld());
    }

    public String getMetadata(String key) {
        return fileLevelData.getMetadata(key);
    }
    
}

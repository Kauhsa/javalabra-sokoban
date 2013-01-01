/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.level.yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import kauhsa.sokoban.core.World;
import kauhsa.sokoban.level.Level;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author mika
 */
public class YAMLLevel implements Level {
    
    private final YAMLLevelData fileLevelData;
    
    public YAMLLevel(InputStream inputStream) {        
        Yaml yaml = new Yaml();
        fileLevelData = yaml.loadAs(inputStream, YAMLLevelData.class);
    }

    public World getWorld() throws InvalidYAMLLevelException {
        return YAMLLevelWorldCreator.createWorld(fileLevelData.getWorldString());
    }

    public String getName() {
        return fileLevelData.getMetadata("name");
    }

    public String getAuthor() {
        return fileLevelData.getMetadata("author");
    }
    
}

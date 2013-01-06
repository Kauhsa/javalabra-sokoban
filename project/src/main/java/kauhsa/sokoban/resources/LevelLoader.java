/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import kauhsa.sokoban.level.Level;
import kauhsa.sokoban.level.yaml.InvalidYAMLLevelException;
import kauhsa.sokoban.level.yaml.YAMLLevel;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author mika
 */
public class LevelLoader {
    private static final String LEVELS_RESOURCE_LOCATION = "levels/levels.txt";
    
    public static List<Level> getLevels() {
        ArrayList<Level> levels = new ArrayList<Level>();        
        InputStream levelsFile = ResourceLoader.getResourceAsStream(LEVELS_RESOURCE_LOCATION);
        Scanner scanner = new Scanner(levelsFile);
        
        while (scanner.hasNextLine()) {
            String currentResourceLocation = scanner.nextLine();            
            if (currentResourceLocation.isEmpty()) {
                continue;
            }
            
            InputStream currentResource = ResourceLoader.getResourceAsStream(currentResourceLocation);
            
            try {
                Level currentLevel = new YAMLLevel(currentResource);
                levels.add(currentLevel);
            } catch (InvalidYAMLLevelException ex) {
                Log.warn("Could not add level " + currentResourceLocation + "to level list: " + ex.getMessage());
            }
        }
        
        return levels;
    }
}

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
 * Class containing helper functions for loading levels bundled with the game.
 */
public class LevelLoader {
    private static final String LEVELS_RESOURCE_LOCATION = "levels/levels.txt";
    
    /**
     * Get List of Levels bundled with the game.
     * 
     * @return List of Level objects.
     */
    public static List<Level> getLevels() {
        ArrayList<Level> levels = new ArrayList<Level>();        
        Scanner scanner = getLevelResourceScanner();
        
        while (scanner.hasNextLine()) {
            String currentResourceLocation = scanner.nextLine();            
            if (currentResourceLocation.isEmpty()) {
                continue;
            }
            
            InputStream currentResource = ResourceLoader.getResourceAsStream(currentResourceLocation);
            addLevelToList(currentResource, levels, currentResourceLocation);
        }
        
        return levels;
    }

    private static Scanner getLevelResourceScanner() {
        InputStream levelsFile = ResourceLoader.getResourceAsStream(LEVELS_RESOURCE_LOCATION);
        Scanner scanner = new Scanner(levelsFile);
        return scanner;
    }
    
    private static void addLevelToList(InputStream currentResource, ArrayList<Level> levels, String currentResourceLocation) {
        try {
            Level currentLevel = new YAMLLevel(currentResource);
            levels.add(currentLevel);
        } catch (InvalidYAMLLevelException ex) {
            Log.warn("Could not add level " + currentResourceLocation + "to level list: " + ex.getMessage());
        }
    }
}

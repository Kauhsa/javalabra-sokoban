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

    /**
     * Get List of Levels bundled with the game.
     *
     * @param levelsResourceLocation resource location of levels file
     * @return List of Level objects.
     */
    public static List<Level> getLevels(String levelsResourceLocation) {
        ArrayList<Level> levels = new ArrayList<Level>();
        Scanner scanner = getLevelResourceScanner(levelsResourceLocation);

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

    /**
     * Get scanner containing the lines from file listing all levels in the
     * game.
     * 
     * @param levelsResourceLocation resource location of levels file
     * @return Scanner containing lines of level resource locations.
     */
    private static Scanner getLevelResourceScanner(String levelsResourceLocation) {
        InputStream levelsFile = ResourceLoader.getResourceAsStream(levelsResourceLocation);
        Scanner scanner = new Scanner(levelsFile);
        return scanner;
    }

    /**
     * Load Level from YAML data and add it to Level list.
     * 
     * @param YAMLLevelInputStream InputStream containing YAML-formatted level data.
     * @param levels ArrayList where the Level will be added.
     * @param levelLocation resource location of the level.
     */
    private static void addLevelToList(InputStream YAMLLevelInputStream, ArrayList<Level> levels, String levelLocation) {
        try {
            Level currentLevel = new YAMLLevel(YAMLLevelInputStream);
            levels.add(currentLevel);
        } catch (InvalidYAMLLevelException ex) {
            Log.warn("Could not add level " + levelLocation + "to level list: " + ex.getMessage());
        }
    }
}

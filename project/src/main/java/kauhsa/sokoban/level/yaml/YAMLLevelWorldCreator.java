package kauhsa.sokoban.level.yaml;

import java.util.Scanner;
import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.utils.Point;
import kauhsa.sokoban.core.worldobjects.Box;
import kauhsa.sokoban.core.worldobjects.BoxTarget;
import kauhsa.sokoban.core.worldobjects.Floor;
import kauhsa.sokoban.core.worldobjects.Player;
import kauhsa.sokoban.core.worldobjects.Wall;

/**
 * Class for generating Worlds from String-type data used in YAML levels.
 */
public final class YAMLLevelWorldCreator {
    
    private final static char EMPTY_CHAR = '.';
    private final static char FLOOR_CHAR = '_';
    private final static char WALL_CHAR = '#';    
    private final static char PLAYER_CHAR = '@';
    private final static char BOX_CHAR = 'o';    
    private final static char BOX_TARGET_CHAR = 'x';
    
    /**
     * Create new World from String.
     * 
     * @param worldString String representation of World.
     * @return fresh copy of World that worldString represents.
     * @throws InvalidYAMLLevelException if worldString is invalid.
     */
    public static World createWorld(String worldString) throws InvalidYAMLLevelException {
        World world = createEmptyWorld(worldString);
        Scanner scanner = new Scanner(worldString);
        
        int y = 0;
        
        while (scanner.hasNextLine()) {
            String currentRow = scanner.nextLine();
            for (int x = 0; x < currentRow.length(); x++) {
                handlePoint(world, new Point(x, y), currentRow.charAt(x));
            }
            y++;
        }
        
        return world;
    }
    
    private static World createEmptyWorld(String worldString) throws InvalidYAMLLevelException {
        /* Because we can not create World object without knowing it's width and
         * height, we have to traverse through it twice. This is the first
         * traversal, where we keep track of the width and height. */
        
        Scanner scanner = new Scanner(worldString);
        int width = scanner.nextLine().length();
        int height = 1;
        
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (row.length() != width) {
                throw new InvalidYAMLLevelException("All rows on world are not the same width");
            }
            height++;
        }
        
        return new World(width, height);
    }
    
    private static void handlePoint(World world, Point point, char c) throws InvalidYAMLLevelException {
        WorldObject worldObject = worldObjectFromChar(c);
        if (worldObject != null) {
            world.placeWorldObject(worldObject, point);
        }
        
        // These type of objects should always have floor beneath them
        if (c == PLAYER_CHAR || c == BOX_CHAR || c == BOX_TARGET_CHAR) {
            world.placeWorldObject(new Floor(), point);
        }
    }
    
    private static WorldObject worldObjectFromChar(char c) throws InvalidYAMLLevelException {
        if (c == WALL_CHAR) {
            return new Wall();
        } else if (c == FLOOR_CHAR) {
            return new Floor();
        } else if (c == PLAYER_CHAR) {
            return new Player();
        } else if (c == BOX_CHAR) {
            return new Box();
        } else if (c == BOX_TARGET_CHAR) {
            return new BoxTarget();
        } else if (c == EMPTY_CHAR) {
            return null;
        }
        
        throw new InvalidYAMLLevelException(String.format("Unknown char '%s' in world data", c));
    }
}

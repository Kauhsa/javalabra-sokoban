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
 *
 * @author mika
 */
public final class YAMLLevelWorldCreator {
    
    private final static char FLOOR_CHAR = '.';
    private final static char WALL_CHAR = '#';    
    private final static char PLAYER_CHAR = '@';
    private final static char BOX_CHAR = 'o';    
    private final static char BOX_TARGET_CHAR = 'x';
    
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
        world.placeWorldObject(new Floor(), point);
        if (c != FLOOR_CHAR) {
            WorldObject worldObject = worldObjectFromChar(c);
            world.placeWorldObject(worldObject, point);
        }
    }
    
    private static WorldObject worldObjectFromChar(char c) throws InvalidYAMLLevelException {
        if (c == WALL_CHAR) {
            return new Wall();
        } else if (c == PLAYER_CHAR) {
            return new Player();
        } else if (c == BOX_CHAR) {
            return new Box();
        } else if (c == BOX_TARGET_CHAR) {
            return new BoxTarget();
        }
        
        throw new InvalidYAMLLevelException(String.format("Unknown char %s in world data", c));
    }
}

package kauhsa.sokoban.level.yaml;

import java.util.Scanner;
import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.utils.Point;
import kauhsa.sokoban.core.worldobjects.Box;
import kauhsa.sokoban.core.worldobjects.Floor;
import kauhsa.sokoban.core.worldobjects.Player;
import kauhsa.sokoban.core.worldobjects.Wall;

/**
 *
 * @author mika
 */
public class YAMLLevelWorldCreator {
    
    private final static char FLOOR_CHAR = '.';
    private final static char WALL_CHAR = '#';    
    private final static char PLAYER_CHAR = '@';
    private final static char BOX_CHAR = 'o';
    
    public static World createWorld(String worldString) throws InvalidYAMLLevelException {
        World world = createEmptyWorld(worldString);
        Scanner scanner = new Scanner(worldString);
        
        int y = 0;
        
        while (scanner.hasNextLine()) {
            String currentRow = scanner.nextLine();
            for (int x = 0; x < currentRow.length(); x++) {
                placeCharToWorld(currentRow.charAt(x), new Point(x, y), world);
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
    
    private static void placeCharToWorld(char c, Point point, World world) throws InvalidYAMLLevelException {
        WorldObject worldObject = worldObjectFromChar(c);
        world.placeWorldObject(worldObject, point);
    }
    
    private static WorldObject worldObjectFromChar(char c) throws InvalidYAMLLevelException {
        if (c == FLOOR_CHAR) {
            return new Floor();
        } else if (c == WALL_CHAR) {
            return new Wall();
        } else if (c == PLAYER_CHAR) {
            return new Player();
        } else if (c == BOX_CHAR) {
            return new Box();
        }
        
        throw new InvalidYAMLLevelException(String.format("Unknown char %s in world data", c));
    }
}

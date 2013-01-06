/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.game;

import java.io.InputStream;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;
import kauhsa.sokoban.core.utils.Direction;
import kauhsa.sokoban.core.utils.Point;
import kauhsa.sokoban.level.InvalidLevelException;
import kauhsa.sokoban.level.Level;
import kauhsa.sokoban.level.yaml.YAMLLevel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mika
 */
public class SokobanGameTest {

    private SokobanGame game;
    private int moveCount;

    @Before
    public void setUp() throws InvalidLevelException {
        InputStream resource = this.getClass().getResourceAsStream("/kauhsa/sokoban/level/yaml/valid2.yaml");
        Level level = new YAMLLevel(resource);
        game = new SokobanGame(level);
    }

    public WorldObject getPlayer() {
        return game.getWorld().getWorldObjectsOfType(WorldObjectType.PLAYER).get(0);
    }

    public void moveAndAssertPosition(Direction d, Point p) {
        Point oldPoint = getPlayer().getPosition();
        game.movePlayer(d);
        Point newPoint = getPlayer().getPosition();        
        assertEquals(p, newPoint);
        
        // If the position got changed, move count should get up by one
        if (!oldPoint.equals(newPoint)) {
            moveCount++;
        }         
        assertEquals(moveCount, game.getMoveCount());
    }

    @Test
    public void testMovement() {
        moveAndAssertPosition(Direction.UP, new Point(0, 0));
        moveAndAssertPosition(Direction.RIGHT, new Point(1, 0));
        moveAndAssertPosition(Direction.DOWN, new Point(1, 1));
        moveAndAssertPosition(Direction.LEFT, new Point(0, 1));
        moveAndAssertPosition(Direction.UP, new Point(0, 0));
    }
    
    @Test
    public void testFinished() {
        assertFalse(game.isFinished());
        game.movePlayer(Direction.DOWN);
        game.movePlayer(Direction.DOWN);
        game.movePlayer(Direction.RIGHT);
        game.movePlayer(Direction.RIGHT);
        game.movePlayer(Direction.RIGHT);
        assertTrue(game.isFinished());
        game.movePlayer(Direction.RIGHT);
        assertTrue("Game still finished when the current status of world is not", game.isFinished());
    }
}

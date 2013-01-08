package kauhsa.sokoban.game;

import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.WorldMovementHandler;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;
import kauhsa.sokoban.core.WorldStatus;
import kauhsa.sokoban.core.utils.Direction;
import kauhsa.sokoban.level.InvalidLevelException;
import kauhsa.sokoban.level.Level;

/**
 * Class that implements Sokoban game.
 */
public class SokobanGame {

    private final World world;
    private final WorldMovementHandler worldMovementHandler;
    private final WorldStatus worldStatus;
    private boolean isFinished;
    private final Level level;
    private int moveCount;

    /**
     * Initialize a new Sokoban game.
     *
     * @param level level that will be loaded.
     * @throws InvalidLevelException if the level given as parameter is invalid
     * in some way.
     */
    public SokobanGame(Level level) throws InvalidLevelException {
        this.level = level;
        world = level.generateWorld();
        worldMovementHandler = new WorldMovementHandler(world);
        worldStatus = new WorldStatus(world);
    }

    /**
     * Throw an IllegalStateException if Game does not have a World set.
     * 
     * @throws IllegalStateException if world is not set.
     */
    private void worldLoadedCheck() {
        if (world == null) {
            throw new IllegalStateException("World is not initalized");
        }
    }

    /**
     * Update the finished status of the game.
     */
    private void updateIsFinished() {
        if (!isFinished && worldStatus.isFinished()) {
            isFinished = true;
        }
    }

    /**
     * Get the specific player WorldObject in World.
     * 
     * @param index index of the returned player.
     * @return player WorldObject.
     */
    private WorldObject getPlayer(int index) {
        return world.getWorldObjectsOfType(WorldObjectType.PLAYER).get(index);
    }

    /**
     * Move the player to wanted direction.
     *
     * @param direction wanted direction.
     * @param index player we want to move.
     */
    private void movePlayer(Direction direction, int index) {
        worldLoadedCheck();
        if (worldMovementHandler.move(getPlayer(0), direction)) {
            moveCount++;
        }
        updateIsFinished();
    }

    /**
     * Move the first player to wanted direction.
     *
     * @param direction wanted direction.
     */
    public void movePlayer(Direction direction) {
        movePlayer(direction, 0);
    }

    /**
     * Get the current World of this Sokoban game.
     *
     * It should be only used to create an representation of the current
     * situation of world - not to change the status of the world.
     *
     * @return the World of this Sokoban game.
     */
    public World getWorld() {
        return world;
    }
    
    
    /**
     * Return the status of this Sokoban game.
     * 
     * @return return true if this Sokoban game is finished - returns true even
     * if the game is not currently in finished status - some boxes are moved
     * out of their targets, for example.
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Get the Level currently loaded to this game.
     * 
     * @return Level currently loaded to this game.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Get the amount of moves player has done.
     * 
     * Moves that don't actually do anything - for example, trying to move
     * through wall - do not increase moveCount.
     * 
     * @return 
     */
    public int getMoveCount() {
        return moveCount;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.game;

import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.WorldMovementHandler;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;
import kauhsa.sokoban.core.utils.Direction;
import kauhsa.sokoban.level.InvalidLevelException;
import kauhsa.sokoban.level.Level;

/**
 *
 * @author mika
 */
public class Game {
    private final World world;
    private final WorldMovementHandler worldMovementHandler;
    private final WorldStatus worldStatus;
    
    private boolean isFinished;
        
    public Game(Level level) throws InvalidLevelException {
        world = level.getWorld();
        worldMovementHandler = new WorldMovementHandler(world);
        worldStatus = new WorldStatus(world);
    }
    
    private void worldLoadedCheck() {
        if (world == null) {
            throw new IllegalStateException("World is not initalized");
        }
    }
    
    private void isFinishedCheck() {
        if (isFinished) {
            throw new IllegalStateException("Current world is finished");
        }
    }
    
    private void updateIsFinished() {
        if (worldStatus.isFinished()) {
            isFinished = true;
        }
    }
    
    private WorldObject getPlayer() {
        return world.getWorldObjectsOfType(WorldObjectType.PLAYER).get(0);
    }
    
    public void movePlayer(Direction direction) {
        worldLoadedCheck();
        isFinishedCheck();
        worldMovementHandler.move(getPlayer(), direction);
        updateIsFinished();
        
    }
    
    public World getWorld() {
        return world;
    }
    
    public boolean isFinished() {
        return isFinished;
    }
}

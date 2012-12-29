package kauhsa.sokoban.core.worldobjects;

import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;

/**
 *
 * @author mika
 */
public class Player extends WorldObject {

    @Override
    public WorldObjectType getType() {
        return WorldObjectType.PLAYER;
    }

    @Override
    public boolean canMove(WorldObject worldObject) {
        // You can't move anything to player
        return false;
    }

    @Override
    public boolean canPush(WorldObject worldObject) {
        // Players can't be pushed
        return false;
    }
    
}
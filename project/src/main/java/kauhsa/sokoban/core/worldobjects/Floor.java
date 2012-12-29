package kauhsa.sokoban.core.worldobjects;

import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;

/**
 *
 * @author mika
 */
public class Floor extends WorldObject {

    @Override
    public WorldObjectType getType() {
        return WorldObjectType.FLOOR;
    }

    @Override
    public boolean canMove(WorldObject worldObject) {
        // Floor does not restrict movement
        return true;
    }

    @Override
    public boolean canPush(WorldObject worldObject) {
        // Floor can not be pushed
        return false;
    }
    
}

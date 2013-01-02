package kauhsa.sokoban.core.worldobjects;

import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;

/**
 * WorldObject for floor. Objects can not move if there is no WorldObjects in
 * wanted location - thus Floor is important.
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

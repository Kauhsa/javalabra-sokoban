package kauhsa.sokoban.core.worldobjects;

import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;

/**
 * WorldObject for Sokoban's "box targets" - also called "storage locations".
 * Sokoban game is over, when all boxes are in same locations than these.
 */
public class BoxTarget extends WorldObject {

    @Override
    public WorldObjectType getType() {
        return WorldObjectType.BOXTARGET;
    }

    @Override
    public boolean canMove(WorldObject worldObject) {
        // Box target does not restrict movement
        return true;
    }

    @Override
    public boolean canPush(WorldObject worldObject) {
        // Box target cannot be pushed
        return false;
    }    
}

package kauhsa.sokoban.core.worldobjects;

import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;

/**
 *
 * @author mika
 */
public class BoxTarget extends WorldObject {

    @Override
    public WorldObjectType getType() {
        return WorldObjectType.FLOOR;
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

package kauhsa.sokoban.core.worldobjects;

import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;

/**
 * WorldObject for Sokoban's wall. Nothing can move to same locations than
 * these.
 */
public class Wall extends WorldObject {

    @Override
    public WorldObjectType getType() {
        return WorldObjectType.WALL;
    }

    @Override
    public boolean canMove(WorldObject worldObject) {
        // Nothing can move to wall
        return false;
    }

    @Override
    public boolean canPush(WorldObject worldObject) {
        // Wall can not be pushed
        return false;
    }
    
}
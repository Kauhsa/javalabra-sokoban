package kauhsa.sokoban.core.worldobjects;

import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;

/**
 * WorldObject for Sokoban's box. Player WorldObjects can push this object.
 */
public class Box extends WorldObject {

    @Override
    public WorldObjectType getType() {
        return WorldObjectType.BOX;
    }

    @Override
    public boolean canMove(WorldObject worldObject) {
        // You can't move to same tile where Box is
        return false;
    }

    @Override
    public boolean canPush(WorldObject worldObject) {
        // Only player can push Box
        return worldObject.getType() == WorldObjectType.PLAYER;
    }
    
}

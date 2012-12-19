package kauhsa.sokoban.core;

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
        // Floor does restrict movement
        return true;
    }
    
}

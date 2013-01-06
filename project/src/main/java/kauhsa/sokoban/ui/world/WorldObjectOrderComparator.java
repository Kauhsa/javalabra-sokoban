package kauhsa.sokoban.ui.world;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;

/**
 * Comparator class that defines the rendering order between two WorldObjects.
 */
public class WorldObjectOrderComparator implements Comparator<WorldObject> {

    private final Map<WorldObjectType, Integer> orderMap = new EnumMap<WorldObjectType, Integer>(WorldObjectType.class);
    
    public WorldObjectOrderComparator() {
        orderMap.put(WorldObjectType.FLOOR, 1);
        orderMap.put(WorldObjectType.BOXTARGET, 2);
        orderMap.put(WorldObjectType.BOX, 3);
        orderMap.put(WorldObjectType.WALL, 4);
        orderMap.put(WorldObjectType.PLAYER, 5);
    }
    
    public int compare(WorldObject firstWO, WorldObject secondWO) {
        return orderMap.get(firstWO.getType()).compareTo(orderMap.get(secondWO.getType()));
    }    
}

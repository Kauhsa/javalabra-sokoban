package kauhsa.sokoban.core;

/**
 * Enum for different object types.
 * 
 * This information is used for bits of logic in World and movement, but can
 * also be used, for example, to define how the WorldObject should be shown in
 * UI.
 */
public enum WorldObjectType {
    FLOOR,
    WALL,
    BOX,
    BOXTARGET,
    PLAYER
}

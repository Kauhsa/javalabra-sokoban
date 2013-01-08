package kauhsa.sokoban.ui.gamestates;

/**
 * Enum for different states the game can be in - exists mainly because Slick
 * requires an unique identifier for each GameState and enums provide a
 * convenient way of doing that.
 */
public enum GameStates {
    MAIN_MENU, IN_GAME, LEVEL_MENU, LEVEL_DONE;
}

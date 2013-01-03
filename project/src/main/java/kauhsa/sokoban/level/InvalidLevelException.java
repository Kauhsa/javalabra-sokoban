package kauhsa.sokoban.level;

/**
 * Base exception for problems in loading level or generating it's World.
 */
public class InvalidLevelException extends Exception {

    public InvalidLevelException(String s) {
        super(s);
    }
}

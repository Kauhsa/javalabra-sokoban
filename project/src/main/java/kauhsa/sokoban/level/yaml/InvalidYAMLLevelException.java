package kauhsa.sokoban.level.yaml;

import kauhsa.sokoban.level.InvalidLevelException;

/**
 * Exception for problems within YAMLLevel.
 */
public class InvalidYAMLLevelException extends InvalidLevelException {

    public InvalidYAMLLevelException(String s) {
        super(s);
    }
}

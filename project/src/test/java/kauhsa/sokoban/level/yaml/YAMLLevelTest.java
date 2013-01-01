package kauhsa.sokoban.level.yaml;

import kauhsa.sokoban.level.yaml.YAMLLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import kauhsa.sokoban.level.InvalidLevelException;
import kauhsa.sokoban.level.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mika
 */
public class YAMLLevelTest {
    private Level getYAMLLevelFromResource(String s) {
        InputStream levelFile = YAMLLevelTest.class.getResourceAsStream(s);
        return new YAMLLevel(levelFile);
    }
    
    @Test
    public void metaDataTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("test_level.yaml");
        assertEquals("Kauhsa", level.getAuthor());
        assertEquals("TestLevel", level.getName());
    }
    
}

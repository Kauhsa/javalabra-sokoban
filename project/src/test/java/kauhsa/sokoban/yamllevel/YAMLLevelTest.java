package kauhsa.sokoban.yamllevel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import kauhsa.sokoban.core.Level;
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
    public void metaDataTest() throws FileNotFoundException {
        Level level = getYAMLLevelFromResource("test_level.yaml");
        assertEquals("Kauhsa", level.getAuthor());
        assertEquals("TestLevel", level.getName());
    }
    
}

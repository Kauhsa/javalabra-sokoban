package kauhsa.sokoban.level.yaml;

import kauhsa.sokoban.level.yaml.YAMLLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import kauhsa.sokoban.core.World;
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
        Level level = getYAMLLevelFromResource("valid1.yaml");
        assertEquals("Kauhsa", level.getAuthor());
        assertEquals("TestLevel", level.getName());
    }
    
    @Test
    public void validWorldTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("valid1.yaml");
        World world = level.getWorld();
        assertEquals(5, world.getWidth());        
        assertEquals(3, world.getHeight());
    }
    
    @Test(expected=InvalidYAMLLevelException.class)
    public void inconsistentWidthsTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("differentWidths.yaml");
        World world = level.getWorld();
    }
    
    @Test(expected=InvalidYAMLLevelException.class)
    public void unknownCharactersTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("unknownCharacters.yaml");
        World world = level.getWorld();
    }
    
}

package kauhsa.sokoban.level.yaml;

import kauhsa.sokoban.level.yaml.YAMLLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import kauhsa.sokoban.core.World;
import kauhsa.sokoban.core.WorldObject;
import kauhsa.sokoban.core.WorldObjectType;
import kauhsa.sokoban.core.utils.Point;
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
    private Level getYAMLLevelFromResource(String s) throws InvalidLevelException {
        InputStream levelFile = YAMLLevelTest.class.getResourceAsStream(s);
        return new YAMLLevel(levelFile);
    }
    
    @Test
    public void metaDataTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("valid1.yaml");
        assertEquals("Kauhsa", level.getMetadata("author"));
        assertEquals("TestLevel", level.getMetadata("name"));
    }
    
    @Test
    public void noMetaDataTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("noMetaData.yaml");
        assertNull(level.getMetadata("name"));
    }
    
    @Test(expected=InvalidLevelException.class)
    public void noWorldDataTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("noWorldData.yaml");
    }
    
    @Test(expected=InvalidLevelException.class)
    public void noPlayerTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("noPlayer.yaml");
        World world = level.generateWorld();
    }    
    
    @Test
    public void widthAndHeightTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("valid1.yaml");
        World world = level.generateWorld();
        assertEquals(5, world.getWidth());        
        assertEquals(3, world.getHeight());
    }
    
    private boolean isObjectOfTypeInCollection(Collection<WorldObject> worldObjects, WorldObjectType worldObjectType) {
        for (WorldObject worldObject : worldObjects) {
            if (worldObject.getType() == worldObjectType) {
                return true;
            }
        }
        
        return false;
    }
    
    private void worldHasObjectInPointTest(World world, Point point, WorldObjectType worldObjectType) {
        assertTrue(isObjectOfTypeInCollection(world.getWorldObjectsInPoint(point), worldObjectType));
    }
    
    @Test
    public void objectsCreatedCorrectlyTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("valid1.yaml");
        World world = level.generateWorld();
        
        worldHasObjectInPointTest(world, new Point(4, 0), WorldObjectType.WALL);
        worldHasObjectInPointTest(world, new Point(4, 1), WorldObjectType.WALL);
        worldHasObjectInPointTest(world, new Point(4, 2), WorldObjectType.WALL);
        worldHasObjectInPointTest(world, new Point(0, 1), WorldObjectType.PLAYER);
        worldHasObjectInPointTest(world, new Point(3, 2), WorldObjectType.BOX);        
        worldHasObjectInPointTest(world, new Point(2, 2), WorldObjectType.BOXTARGET);
        
        worldHasObjectInPointTest(world, new Point(0, 1), WorldObjectType.FLOOR);        
        worldHasObjectInPointTest(world, new Point(3, 2), WorldObjectType.FLOOR);        
        worldHasObjectInPointTest(world, new Point(2, 2), WorldObjectType.FLOOR);
    }
    
    @Test(expected=InvalidYAMLLevelException.class)
    public void inconsistentWidthsTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("differentWidths.yaml");
        World world = level.generateWorld();
    }
    
    @Test(expected=InvalidYAMLLevelException.class)
    public void unknownCharactersTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("unknownCharacters.yaml");
        World world = level.generateWorld();
    }
    
    @Test(expected=InvalidYAMLLevelException.class)
    public void gibberishDataTest() throws InvalidLevelException {
        Level level = getYAMLLevelFromResource("gibberish.yaml");
    }
    
}

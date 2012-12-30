package kauhsa.sokoban.yamllevel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    @Test
    public void fileLevelTest() throws FileNotFoundException {
        File file = new File("levels/test_level.yaml");
        FileInputStream fIS = new FileInputStream(file);
        YAMLLevel fileLevel = new YAMLLevel(fIS);
        System.out.println(fileLevel.getAuthor());
        System.out.println(fileLevel.getName());
    }
    
}

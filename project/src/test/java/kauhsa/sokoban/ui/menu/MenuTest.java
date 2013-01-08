
package kauhsa.sokoban.ui.menu;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {
    private Menu<String> menu;
    
    @Before
    public void setUp() {
        menu = new Menu();
    }
    
    private void addItems() {        
        menu.addItem("Thing", "Thung");
        menu.addItem("Thung", "thyng");
    }
    
    @Test
    public void emptyMenuSelectionTest() {
        assertNull(menu.getSelected());
        assertEquals(-1, menu.getSelectedIndex());
        assertEquals(0, menu.getItemCount());
    }
    
    @Test
    public void emptyMenuMoveSelectionTest() {
        menu.moveDown();
        emptyMenuSelectionTest();
        menu.moveUp();
        emptyMenuSelectionTest();
    }
    
    @Test
    public void menuItemCountTest() {
        addItems();
        assertEquals(2, menu.getItemCount());
    }
    
    @Test
    public void menuMoveTest() {
        addItems();
        assertEquals(0, menu.getSelectedIndex());
        menu.moveDown();        
        assertEquals(1, menu.getSelectedIndex());        
        menu.moveUp();
        assertEquals(0, menu.getSelectedIndex());
    }
    
    @Test
    public void menuCannotMoveOutsideItemsTest() {
        addItems();
        assertEquals(0, menu.getSelectedIndex());
        menu.moveDown();        
        menu.moveDown();        
        menu.moveDown();        
        assertEquals(1, menu.getSelectedIndex());        
        menu.moveUp();
        menu.moveUp();
        assertEquals(0, menu.getSelectedIndex());
    }
}

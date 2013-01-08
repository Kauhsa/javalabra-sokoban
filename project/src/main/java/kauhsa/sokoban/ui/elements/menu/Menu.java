package kauhsa.sokoban.ui.elements.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for implementing a simple menu, where there is a list of items and one
 * item is always being currently selected.
 */
public class Menu<T> {
    private int currentSelection = 0;
    private ArrayList<MenuItem<T>> items = new ArrayList<MenuItem<T>>();
    
    /**
     * Add an item to menu.
     * @param title a string representation of this menu item.
     * @param item item to be added.
     */
    public void addItem(String title, T item) {
        MenuItem<T> menuItem = new MenuItem<T>(title, item);
        items.add(menuItem);
    }
     
    /**
     * Select the item previous item in item list.
     */
    public void moveUp() {
        currentSelection = Math.max(0, currentSelection - 1);
    }
    
    /**
     * Select the next item on item list.
     */
    public void moveDown() {
        currentSelection = Math.min(items.size() - 1, currentSelection + 1);
    }
    
    /**
     * Get currently selected item.
     * 
     * @return null if there are no items in menu.
     */
    public T getSelected() {
        if (getSelectedIndex() == -1) {
            return null;
        }
        
        return items.get(getSelectedIndex()).getItem();
    }
    
    /**
     * Get index of currently selected item.
     * 
     * @return -1 if there are no items in menu.
     */
    public int getSelectedIndex() {
        if (currentSelection >= items.size() || currentSelection < 0) {
            return items.size() - 1;
        }        
        
        return currentSelection;        
    }
    
    /**
     * Get the list of all items in menu.
     * 
     * @return List of MenuItems.
     */
    public List<MenuItem<T>> getItems() {
        return items;
    }
    
    /**
     * Get the count of items in menu.
     * 
     * @return count of items in menu.
     */
    public int getItemCount() {
        return items.size();
    }
    
    /**
     * Get an item on specified index.
     * 
     * @param i index of wanted menu item.
     * @return MenuItem of wanted index.
     */
    public MenuItem<T> getItem(int i) {
        return items.get(i);
    }
}

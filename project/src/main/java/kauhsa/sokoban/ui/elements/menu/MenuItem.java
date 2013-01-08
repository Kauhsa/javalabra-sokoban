package kauhsa.sokoban.ui.elements.menu;

/**
 * Container class for menu item and it's String representation.
 */
public class MenuItem<T> {
    private final String label;
    private final T item;
    
    /**
     * Create a new menu item.
     * 
     * @param label text representation of the menu item.
     * @param item menu item.
     */
    public MenuItem(String label, T item) {
        this.label = label;
        this.item = item;
    }

    /**
     * Get the String representation of the menu item.
     * 
     * @return String representation of the menu item.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the menu item.
     * 
     * @return menu item.
     */
    public T getItem() {
        return item;
    }
}
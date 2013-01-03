/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.menu;

/**
 *
 * @author mika
 */
public class MenuItem<T> {
    private final String label;
    private final T item;
    
    public MenuItem(String label, T item) {
        this.label = label;
        this.item = item;
    }

    public String getLabel() {
        return label;
    }

    public T getItem() {
        return item;
    }
}

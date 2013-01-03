/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.menu;

import java.util.ArrayList;

/**
 *
 * @author mika
 */
public class Menu<T> {
    private int currentSelection = 0;
    private ArrayList<MenuItem<T>> items = new ArrayList<MenuItem<T>>();
    
    public void addItem(String title, T item) {
        MenuItem<T> menuItem = new MenuItem<T>(title, item);
        items.add(menuItem);
    }
       
    public void moveUp() {
        currentSelection = Math.max(0, currentSelection - 1);
    }
    
    public void moveDown() {
        currentSelection = Math.min(items.size() - 1, currentSelection + 1);
    }
    
    public T getSelected() {
        return items.get(currentSelection).getItem();
    }
    
    public int getSelectedIndex() {
        return currentSelection;
    }
    
    public ArrayList<MenuItem<T>> getItems() {
        return items;
    }
    
    public int getItemCount() {
        return items.size();
    }
    
    public MenuItem<T> getItem(int i) {
        return items.get(i);
    }
}

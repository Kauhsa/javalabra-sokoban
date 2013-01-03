/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

/**
 *
 * @author mika
 */
public class MenuRenderer {
    private final Menu menu;
    private Font font;
    private boolean horizontalCentered;
    private boolean verticalCentered;
    private Color inactiveColor;
    private Color activeColor;
    
    private float width;
    private float height;
    
    private int currentScreenPosition;
    
    public MenuRenderer(Menu menu) {
        this.menu = menu;
        this.font = null;
        this.horizontalCentered = true;
        this.verticalCentered = true;
        this.inactiveColor = Color.white;
        this.activeColor = Color.yellow;
        this.currentScreenPosition = menu.getSelectedIndex();
    }

    public Menu getMenu() {
        return menu;
    }

    public Font getFont() {
        return font;
    }

    public boolean getHorizontalCentered() {
        return horizontalCentered;
    }

    public boolean getVerticalCentered() {
        return verticalCentered;
    }

    public Color getInactiveColor() {
        return inactiveColor;
    }

    public Color getActiveColor() {
        return activeColor;
    }
    
    public void setFont(Font font) {
        this.font = font;
    }
    
    public void setInactiveColor(Color inactiveColor) {
        this.inactiveColor = inactiveColor;
    }

    public void setActiveColor(Color activeColor) {
        this.activeColor = activeColor;
    }
    
    public void setHorizontalCentered(boolean horizontalCentered) {
        this.horizontalCentered = horizontalCentered;
    }
    
    public void setVerticalCentered(boolean verticalCentered) {
        this.verticalCentered = verticalCentered;
    }

    
    private float getMenuItemVerticalPosition(int index) {
        float verticalOffset = 0;
        if (verticalCentered) {
            verticalOffset = height / 2 - (menuItemsShown() * font.getLineHeight()) / 2;
        }
        return font.getLineHeight() * (index - currentScreenPosition) + verticalOffset;
    }
    
    private float getHorizontalPosition(String s) {
        if (!horizontalCentered) {
            return 0;
        } else {
            return (width / 2) - (font.getWidth(s) / 2);
        }
    }
    
    private int menuItemsFit() {
        return Math.max((int) height / font.getLineHeight(), 1);
    }
    
    private int menuItemsShown() {
        return Math.min(menuItemsFit(), menu.getItemCount());
    }
    
    private boolean isMenuItemShownOnScreen(int index) {
        return (index >= currentScreenPosition && index <= lastShownMenuItemIndex());
    }
    
    private int lastShownMenuItemIndex() {
        return currentScreenPosition + menuItemsShown() - 1;
    }
    
    private String shortenTextToWidth(String s) {
        if (font.getWidth(s) <= width) {
            return s;
        } else if (font.getWidth("...") > width) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder(s);
        sb.append("...");
        
        while (sb.length() != 3 && font.getWidth(sb.toString()) > width) {
            sb.deleteCharAt(sb.length() - 4);
        }
        
        return sb.toString();
    }
    
    private void setGraphicsFont(Graphics graphics) {
        if (font == null) {
            font = graphics.getFont();
        }
        
        graphics.setFont(font);
    }

    private void setMenuItemGraphicsColor(Graphics graphics, int pos) {
        if (pos == menu.getSelectedIndex()) {
            graphics.setColor(activeColor);
        } else {
            graphics.setColor(inactiveColor);
        }
    }
    
    private void updateSizeInfo(float width, float height) {
        this.width = width;
        this.height = height;
    }
    
    public void render(Graphics graphics, float x, float y, float width, float height) {
        updateSizeInfo(width, height);
        
        Font oldFont = graphics.getFont();
        Color oldColor = graphics.getColor();
        setGraphicsFont(graphics);
        
        int newSelectedIndex = menu.getSelectedIndex();
        if (!isMenuItemShownOnScreen(newSelectedIndex)) {
            updateScreenToShowMenuItem(newSelectedIndex);
        }
       
        for (int menuItemIndex = currentScreenPosition; menuItemIndex <= lastShownMenuItemIndex(); menuItemIndex++) {
            setMenuItemGraphicsColor(graphics, menuItemIndex);
            
            MenuItem menuItem = menu.getItem(menuItemIndex);
            String shortenedMenuItemLabel = shortenTextToWidth(menuItem.getLabel());
            
            float itemXPos = getHorizontalPosition(shortenedMenuItemLabel) + x;
            float itemYPos = getMenuItemVerticalPosition(menuItemIndex) + y;
            
            graphics.drawString(shortenedMenuItemLabel, itemXPos, itemYPos);
        }
        
        graphics.setFont(oldFont);
        graphics.setColor(oldColor);
    }


    private void updateScreenToShowMenuItem(int newSelectedIndex) {
        if (newSelectedIndex < currentScreenPosition) {
            currentScreenPosition = newSelectedIndex;
        } else if (newSelectedIndex > lastShownMenuItemIndex()) {
            currentScreenPosition = newSelectedIndex - menuItemsFit() + 1;
        }
    }
}

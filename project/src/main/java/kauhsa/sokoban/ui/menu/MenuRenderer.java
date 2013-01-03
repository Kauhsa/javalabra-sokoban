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
    private final Font font;
    private final boolean horizontalCentered;
    
    public MenuRenderer(Menu menu, Font font, boolean horizontalCentered) {
        this.menu = menu;
        this.font = font;
        this.horizontalCentered = horizontalCentered;
    }
    
    private int getMenuItemVerticalPosition(int index) {
        return (font.getLineHeight()) * index;
    }
    
    private float getHorizontalPosition(String s, float width) {
        if (!horizontalCentered) {
            return 0;
        } else {
            return (width / 2) - (font.getWidth(s) / 2);
        }
    }
    
    private String shortenTextToWidth(String s, float width) {
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
    
    public void render(Graphics graphics, float x, float y, float width, float height) {
        Font oldFont = graphics.getFont();
        graphics.setFont(font);
        
        int pos = 0;
        for (Object menuItemObject : menu.getItems()) {
            
            if (pos == menu.getSelectedIndex()) {
                graphics.setColor(Color.yellow);
            } else {
                graphics.setColor(Color.white);
            }
            
            MenuItem menuItem = (MenuItem) menuItemObject;
            String shortenedMenuItemLabel = shortenTextToWidth(menuItem.getLabel(), width);
            
            float itemXPos = getHorizontalPosition(shortenedMenuItemLabel, width) + x;
            float itemYPos = getMenuItemVerticalPosition(pos) + y;
            
            graphics.drawString(shortenedMenuItemLabel, itemXPos, itemYPos);
            pos++;
        }
        
        graphics.setFont(oldFont);
    }
}

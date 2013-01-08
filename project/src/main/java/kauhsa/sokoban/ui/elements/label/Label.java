package kauhsa.sokoban.ui.elements.label;

import kauhsa.sokoban.ui.elements.UIElement;
import kauhsa.sokoban.ui.elements.HorizontalAlignment;
import kauhsa.sokoban.ui.elements.VerticalAlignment;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

/**
 * UI element for text.
 * 
 * Text can be shown in different colors and fonts - Label also supports
 * different horizontal and vertical alignments.
 */
public class Label extends UIElement {

    private String text = "";
    private float width;
    private float height;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get width of current text in pixels.
     * 
     * @return width of current text using currently set font.
     */
    private float getTextWidth() {
        return font.getWidth(text);
    }

    /**
     * Get height of current text in pixels.
     * 
     * @return height of current text using currently set font.
     */
    private float getTextHeight() {
        return font.getHeight(text);
    }

    /**
     * Get vertical rendering position of text, depending on currently set
     * vertical alignment.
     * 
     * @return vertical rendering position of text.
     */
    private float getVerticalPosition() {
        if (verticalAlignment == VerticalAlignment.TOP) {
            return 0;
        } else if (verticalAlignment == VerticalAlignment.CENTER) {
            return height / 2 - getTextHeight() / 2;
        } else {
            return height - getTextHeight();
        }
    }

    /**
     * Get horizontal rendering position of text, depending on currently set
     * horizontal alignment.
     * 
     * @return horizontal rendering position of text.
     */
    private float getHorizontalPosition() {
        if (horizontalAlignment == HorizontalAlignment.LEFT) {
            return 0;
        } else if (horizontalAlignment == HorizontalAlignment.MIDDLE) {
            return width / 2 - getTextWidth() / 2;
        } else {
            return width - getTextWidth();
        }
    }
    
    /**
     * Shorten currently text set so it fits to width of this Label.
     * 
     * @return text shortened for width of the Label.
     */
    private String textShortedToWidth() {
        if (font.getWidth(text) <= width) {
            return text;
        } else if (font.getWidth("...") > width) {
            return "";
        }

        StringBuilder sb = new StringBuilder(text);
        sb.append("...");

        while (sb.length() != 3 && font.getWidth(sb.toString()) > width) {
            sb.deleteCharAt(sb.length() - 4);
        }

        return sb.toString();
    }
    
    /**
     * Render the label.
     * 
     * If the text is too long for the width given for label, it will be
     * shortened - although with a bit crude method.
     * 
     * @param graphics Slick Graphics this label will be rendered to.
     * @param x Leftmost coordinate.
     * @param y Topmost coordinate.
     * @param width Maximum width.
     * @param height Maximum height.
     */
    public void render(Graphics graphics, float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
        setGraphicsFontAsFont(graphics);
        
        // Save old font and color so they can be restored after rendering
        Font oldFont = graphics.getFont();
        graphics.setFont(font);
        Color oldColor = graphics.getColor();
        graphics.setColor(color);
        
        graphics.drawString(textShortedToWidth(), (int) getHorizontalPosition() + x, (int) getVerticalPosition() + y);
        
        graphics.setFont(oldFont);
        graphics.setColor(oldColor);
    }

    /**
     * If no font is set for this Label, use the current font of Graphics.
     *      
     * @param graphics currently used Slick Graphics.
     */
    private void setGraphicsFontAsFont(Graphics graphics) {
        if (font == null) {
            font = graphics.getFont();
        }
    }
}

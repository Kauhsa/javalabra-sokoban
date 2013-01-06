package kauhsa.sokoban.ui.label;

import kauhsa.sokoban.ui.utils.HorizontalAlignment;
import kauhsa.sokoban.ui.utils.VerticalAlignment;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

/**
 * UI element for text.
 * 
 * Text can be shown in different colors and fonts - Label also supports
 * different horizontal and vertical alignments.
 */
public class Label {

    private String text = "";
    private Font font = null;
    private Color color = Color.white;
    private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private float width;
    private float height;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    private float getTextWidth() {
        return font.getWidth(text);
    }

    private float getTextHeight() {
        return font.getHeight(text);
    }

    private float getVerticalPosition() {
        if (verticalAlignment == VerticalAlignment.TOP) {
            return 0;
        } else if (verticalAlignment == VerticalAlignment.CENTER) {
            return height / 2 - getTextHeight() / 2;
        } else {
            return height - getTextHeight();
        }
    }

    private float getHorizontalPosition() {
        if (horizontalAlignment == HorizontalAlignment.LEFT) {
            return 0;
        } else if (horizontalAlignment == HorizontalAlignment.MIDDLE) {
            return width / 2 - getTextWidth() / 2;
        } else {
            return width - getTextWidth();
        }
    }

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

    private void setGraphicsFontAsFont(Graphics graphics) {
        if (font == null) {
            font = graphics.getFont();
        }
    }
}

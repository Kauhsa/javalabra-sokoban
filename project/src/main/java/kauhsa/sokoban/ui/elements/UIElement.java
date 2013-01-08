package kauhsa.sokoban.ui.elements;

import kauhsa.sokoban.ui.utils.HorizontalAlignment;
import kauhsa.sokoban.ui.utils.VerticalAlignment;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

/**
 * Abstract base class for different UI elements.
 * 
 * The implementing class does not have necessarily use all the properties
 * UIElement has - it is okay to not use font to anything and to ignore
 * alignment.
 */
abstract public class UIElement {
    protected Font font = null;
    protected Color color = Color.white;
    protected VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
    protected HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    
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
    
    /**
     * Render UI element to wanted position in graphics.
     * 
     * @param graphics Slick Graphics this element will be rendered to.
     * @param x horizontal position of element.
     * @param y vertical position of element.
     * @param width width of element.
     * @param height height of element.
     */
    abstract public void render(Graphics graphics, float x, float y, float width, float height);
}

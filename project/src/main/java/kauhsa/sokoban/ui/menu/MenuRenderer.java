package kauhsa.sokoban.ui.menu;

import kauhsa.sokoban.ui.label.Label;
import kauhsa.sokoban.ui.utils.HorizontalAlignment;
import kauhsa.sokoban.ui.utils.VerticalAlignment;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

/**
 * Class for rendering Menu.
 */
public class MenuRenderer {

    private final Menu menu;
    private Font font = null;
    private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private Color inactiveColor = Color.white;
    private Color activeColor = Color.yellow;
    private float height;
    private int currentScreenPosition = 0;
    
    /**
     * Create a new MenuRenderer.
     * 
     * @param menu menu that will be rendered.
     */
    public MenuRenderer(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public Font getFont() {
        return font;
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

    private float getMenuItemVerticalPosition(int index) {
        float verticalOffset = 0;

        if (verticalAlignment == VerticalAlignment.CENTER) {
            verticalOffset = height / 2 - (menuItemsShown() * singleMenuItemHeight()) / 2;
        } else if (verticalAlignment == VerticalAlignment.BOTTOM) {
            verticalOffset = height - (menuItemsShown() * singleMenuItemHeight());
        }

        return singleMenuItemHeight() * (index - currentScreenPosition) + verticalOffset;
    }

    private int singleMenuItemHeight() {
        return font.getLineHeight();
    }
    
    private int menuItemsFit() {
        return Math.max((int) height / singleMenuItemHeight(), 1);
    }

    private int menuItemsShown() {
        return Math.min(menuItemsFit(), menu.getItemCount());
    }

    private int lastShownMenuItemIndex() {
        return currentScreenPosition + menuItemsShown() - 1;
    }

    /**
     * Render a menu to Slick Graphics.
     * 
     * If there are too many menu items to fit on rendering area, menu will be
     * scrolled so the selected item is always shown.
     * 
     * @param graphics Slick Graphics where the menu will be rendered.
     * @param x Horizontal starting point for rendering.
     * @param y Vertical starting point for rendering.
     * @param width Maximum width of menu.
     * @param height Maximum height of menu.
     */
    public void render(Graphics graphics, float x, float y, float width, float height) {
        this.height = height;
        updateScreenToShowMenuItem(menu.getSelectedIndex());

        for (int menuItemIndex = currentScreenPosition; menuItemIndex <= lastShownMenuItemIndex(); menuItemIndex++) {
            MenuItem menuItem = menu.getItem(menuItemIndex);

            Label label = new Label();
            label.setText(menuItem.getLabel());
            label.setFont(font);
            label.setHorizontalAlignment(horizontalAlignment);
            label.setVerticalAlignment(VerticalAlignment.CENTER);

            if (menuItemIndex == menu.getSelectedIndex()) {
                label.setColor(activeColor);
            } else {
                label.setColor(inactiveColor);
            }

            float verticalRenderPosition = getMenuItemVerticalPosition(menuItemIndex);
            label.render(graphics, x, verticalRenderPosition + y, width, singleMenuItemHeight());
        }
    }

    private void updateScreenToShowMenuItem(int newSelectedIndex) {
        if (newSelectedIndex < currentScreenPosition) {
            currentScreenPosition = newSelectedIndex;
        } else if (newSelectedIndex > lastShownMenuItemIndex()) {
            currentScreenPosition = newSelectedIndex - menuItemsFit() + 1;
        }
    }
}

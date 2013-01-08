package kauhsa.sokoban.ui.menu;

import kauhsa.sokoban.ui.elements.UIElement;
import kauhsa.sokoban.ui.label.Label;
import kauhsa.sokoban.ui.utils.VerticalAlignment;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Class for rendering Menu.
 */
public class MenuRenderer extends UIElement {

    private final Menu menu;
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

    public Color getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(Color activeColor) {
        this.activeColor = activeColor;
    }

    /**
     * Get the vertical rendering position of specific menu item. This applies
     * whatever vertical alignment is set for MenuRenderer.
     *
     * @param index index of the wanted menu item.
     * @return vertical rendering position of menu item.
     */
    private float getMenuItemVerticalPosition(int index) {
        float verticalOffset = 0;

        if (verticalAlignment == VerticalAlignment.CENTER) {
            verticalOffset = height / 2 - (menuItemsShown() * singleMenuItemHeight()) / 2;
        } else if (verticalAlignment == VerticalAlignment.BOTTOM) {
            verticalOffset = height - (menuItemsShown() * singleMenuItemHeight());
        }

        return singleMenuItemHeight() * (index - currentScreenPosition) + verticalOffset;
    }

    /**
     * Get height of one menu item.
     *
     * @return height of one menu item in pixels.
     */
    private int singleMenuItemHeight() {
        return font.getLineHeight();
    }

    /**
     * Return the amount of menu items that can be rendered to screen at the
     * same time.
     * 
     * It is guaranteed that at least one menu item can be rendered to screen.
     *
     * @return amount of menu items that currently fit to screen.
     */
    private int menuItemsFit() {
        return Math.max((int) height / singleMenuItemHeight(), 1);
    }

    /**
     * Get the amount of menu items that are shown on the screen.
     * 
     * @return amount of menu items rendered.
     */
    private int menuItemsShown() {
        return Math.min(menuItemsFit(), menu.getItemCount());
    }

    /**
     * Get the index of last shown menu item.
     * 
     * @return index of last shown menu item.
     */
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
                label.setColor(color);
            }

            float verticalRenderPosition = getMenuItemVerticalPosition(menuItemIndex);
            label.render(graphics, x, verticalRenderPosition + y, width, singleMenuItemHeight());
        }
    }

    /**
     * Update the position of menu to show a specific menu item.
     * 
     * @param menuItemIndex index of menu item to be shown on menu.
     */
    private void updateScreenToShowMenuItem(int menuItemIndex) {
        if (menuItemIndex < currentScreenPosition) {
            currentScreenPosition = menuItemIndex;
        } else if (menuItemIndex > lastShownMenuItemIndex()) {
            currentScreenPosition = menuItemIndex - menuItemsFit() + 1;
        }
    }
}

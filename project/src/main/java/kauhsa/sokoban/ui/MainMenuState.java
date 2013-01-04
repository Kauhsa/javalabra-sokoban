/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui;

import kauhsa.sokoban.game.SokobanGame;
import kauhsa.sokoban.level.InvalidLevelException;
import kauhsa.sokoban.level.Level;
import kauhsa.sokoban.level.yaml.YAMLLevel;
import kauhsa.sokoban.resources.FontLoader;
import kauhsa.sokoban.ui.label.Label;
import kauhsa.sokoban.ui.menu.Menu;
import kauhsa.sokoban.ui.menu.MenuRenderer; 
import kauhsa.sokoban.ui.utils.HorizontalAlignment;
import kauhsa.sokoban.ui.utils.VerticalAlignment;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author mika
 */
public class MainMenuState extends BasicGameState {

    private final int EDGE_DISTANCE = 50;
    
    Menu<MainMenuButtons> mainMenu = new Menu<MainMenuButtons>();
    MenuRenderer menuRenderer;
    Label header;

    @Override
    public int getID() {
        return GameStates.MAIN_MENU.ordinal();
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {        
        Font headerFont = FontLoader.loadAwtFontToSlick("Ubuntu", java.awt.Font.PLAIN, 100, gc);        
        header = new Label();
        header.setText("Sokoban!");
        header.setFont(headerFont);
        header.setVerticalAlignment(VerticalAlignment.CENTER);        
        header.setHorizontalAlignment(HorizontalAlignment.MIDDLE);
        
        Font menuFont = FontLoader.loadAwtFontToSlick("Ubuntu", java.awt.Font.PLAIN, 50, gc);        
        menuRenderer = new MenuRenderer(mainMenu);
        menuRenderer.setFont(menuFont);
        menuRenderer.setVerticalAlignment(VerticalAlignment.BOTTOM);
        menuRenderer.setHorizontalAlignment(HorizontalAlignment.RIGHT);       
        
        mainMenu.addItem("Start", MainMenuButtons.START);   
        mainMenu.addItem("Quit", MainMenuButtons.QUIT);     
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        header.render(grphcs, 0, 0, gc.getWidth(), gc.getHeight() / 2);
        menuRenderer.render(grphcs, EDGE_DISTANCE, EDGE_DISTANCE, gc.getWidth() - EDGE_DISTANCE * 2, gc.getHeight() - EDGE_DISTANCE * 2);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_UP)) {
            mainMenu.moveUp();
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            mainMenu.moveDown();
        } else if (input.isKeyPressed(Input.KEY_ENTER)) {
            handleMenuSelection(gc, sbg);
        }
    }

    private void handleMenuSelection(GameContainer gc, StateBasedGame sbg) {
        if (mainMenu.getSelected() == MainMenuButtons.START) {
            sbg.enterState(GameStates.LEVEL_MENU.ordinal());
        } else if (mainMenu.getSelected() == MainMenuButtons.QUIT) {
            gc.exit();
        }
    }
}

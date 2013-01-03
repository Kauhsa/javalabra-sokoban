/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui;

import java.awt.Font;
import kauhsa.sokoban.game.SokobanGame;
import kauhsa.sokoban.level.InvalidLevelException;
import kauhsa.sokoban.level.Level;
import kauhsa.sokoban.level.yaml.YAMLLevel;
import kauhsa.sokoban.ui.menu.Menu;
import kauhsa.sokoban.ui.menu.MenuRenderer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author mika
 */
public class MainMenuState extends BasicGameState {

    public static final int STATE_ID = 2;
    
    private final int EDGE_DISTANCE = 50;
    private final int MENU_START = 0;
    private final int MENU_QUIT = 1;
    Menu<Integer> mainMenu = new Menu<Integer>();
    MenuRenderer menuRenderer;

    @Override
    public int getID() {
        return STATE_ID;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mainMenu.addItem("Start", MENU_START);
        mainMenu.addItem("Quit", MENU_QUIT);
        
        // TODO: package font with game or something
        Font awtFont = new Font("Ubuntu", Font.PLAIN, 24);
        menuRenderer = new MenuRenderer(mainMenu, loadFont(awtFont), true);
    }

    private UnicodeFont loadFont(Font awtFont) throws SlickException {
        UnicodeFont font = new UnicodeFont(awtFont);
        font.addAsciiGlyphs();
        font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        font.loadGlyphs();
        return font;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
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
        if (mainMenu.getSelected() == MENU_START) {
            startGame(gc, sbg);
        } else if (mainMenu.getSelected() == MENU_QUIT) {
            gc.exit();
        }
    }

    private void startGame(GameContainer gc, StateBasedGame sbg) {
        Level level;
        SokobanGame game = null;
        
        try {
            level = new YAMLLevel(SlickSokobanGame.class.getResourceAsStream("/levels/level1.yaml"));
            game = new kauhsa.sokoban.game.SokobanGame(level);
        } catch (InvalidLevelException ex) {
            gc.exit(); // not done
        }

        InGameState inGameState = (InGameState) sbg.getState(InGameState.STATE_ID);
        inGameState.loadGame(game);
        sbg.enterState(inGameState.getID());
    }
}

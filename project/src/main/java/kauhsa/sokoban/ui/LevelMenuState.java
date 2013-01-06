package kauhsa.sokoban.ui;

import kauhsa.sokoban.game.SokobanGame;
import kauhsa.sokoban.level.InvalidLevelException;
import kauhsa.sokoban.level.Level;
import kauhsa.sokoban.resources.FontLoader;
import kauhsa.sokoban.resources.LevelLoader;
import kauhsa.sokoban.ui.menu.Menu;
import kauhsa.sokoban.ui.menu.MenuRenderer;
import kauhsa.sokoban.ui.world.WorldRenderer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

/**
 * Slick GameState for selecting the level to be played.
 */
public class LevelMenuState extends BasicGameState {
    
    Menu<Level> levelMenu = new Menu<Level>();
    MenuRenderer levelMenuRenderer = new MenuRenderer(levelMenu);
    WorldRenderer worldSampleRenderer = null;

    @Override
    public int getID() {
        return GameStates.LEVEL_MENU.ordinal();
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        populateLevelMenu();
        updateWorldSample();
        levelMenuRenderer.setFont(FontLoader.loadAwtFontToSlick("Ubuntu", java.awt.Font.PLAIN, 50));
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        levelMenuRenderer.render(grphcs, 50, 50, gc.getWidth() / 2 - 100, gc.getHeight() - 100);
        
        if (worldSampleRenderer != null) {
            worldSampleRenderer.render(gc.getWidth() / 2 + 50, 50, gc.getWidth() / 2 - 100, gc.getHeight() - 100);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_UP)) {
            levelMenu.moveUp();
            updateWorldSample();
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            levelMenu.moveDown();
            updateWorldSample();
        } else if (input.isKeyPressed(Input.KEY_ENTER)) {
            handleMenuSelection(gc, sbg);
        } else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(GameStates.MAIN_MENU.ordinal());
        }
    }

    private void populateLevelMenu() {
        for (Level level : LevelLoader.getLevels()) {
            String name = level.getMetadata("name");
            if (name != null) {
                levelMenu.addItem(level.getMetadata("name"), level);
            } else {
                levelMenu.addItem("(Unnamed)", level);
            }
        }
    }

    private void handleMenuSelection(GameContainer gc, StateBasedGame sbg) {
        try {
            SokobanGame game = new SokobanGame(levelMenu.getSelected());            
            InGameState inGameState = (InGameState) sbg.getState(GameStates.IN_GAME.ordinal());
            inGameState.loadGame(game);            
            sbg.enterState(inGameState.getID());
        } catch (InvalidLevelException ex) {
            Log.warn("Could not load level: " + ex.getMessage());
        } catch (SlickException ex) {
            Log.error(ex);
        }
    }

    private void updateWorldSample() throws SlickException {
        try {
            worldSampleRenderer = new WorldRenderer(levelMenu.getSelected().generateWorld(), new CuteTileSet());
        } catch (InvalidLevelException ex) {
            Log.warn("Could not load level: " + ex.getMessage());
        }
    }
    
}

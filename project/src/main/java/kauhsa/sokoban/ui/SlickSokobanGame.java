package kauhsa.sokoban.ui;

import kauhsa.sokoban.resources.LevelLoader;
import kauhsa.sokoban.ui.gamestates.InGameState;
import kauhsa.sokoban.ui.gamestates.LevelDoneState;
import kauhsa.sokoban.ui.gamestates.LevelMenuState;
import kauhsa.sokoban.ui.gamestates.MainMenuState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Sokoban game implemented in Slick.
 */
public class SlickSokobanGame extends StateBasedGame {

    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;
    public static final String WINDOW_TITLE = "Sokoban!";
    
    private static final String LEVELS_RESOURCE_LOCATION = "levels/levels.txt";

    public SlickSokobanGame() {
        super(WINDOW_TITLE);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new SlickSokobanGame());
        app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
        app.setShowFPS(false);
        app.start();
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {        
        this.addState(new MainMenuState());
        this.addState(new LevelMenuState(LevelLoader.getLevels(LEVELS_RESOURCE_LOCATION)));
        this.addState(new InGameState());
        this.addState(new LevelDoneState());
        
        gc.getInput().enableKeyRepeat();
    }
}
package kauhsa.sokoban.ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Hello world!
 *
 */
public class SokobanGame extends StateBasedGame {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final String WINDOW_TITLE = "Sokoban!";

    public SokobanGame() {
        super(WINDOW_TITLE);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new SokobanGame());
        app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new InGameState());
    }
}
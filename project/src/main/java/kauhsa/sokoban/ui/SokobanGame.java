package kauhsa.sokoban.ui;

import kauhsa.sokoban.game.Game;
import kauhsa.sokoban.level.InvalidLevelException;
import kauhsa.sokoban.level.Level;
import kauhsa.sokoban.level.yaml.YAMLLevel;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Hello world!
 *
 */
public class SokobanGame extends StateBasedGame {

    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;
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
        Level level = new YAMLLevel(SokobanGame.class.getResourceAsStream("valid1.yaml"));
        Game game = null;
        try {
            game = new Game(level);
        } catch (InvalidLevelException ex) {
            
        }
        this.addState(new InGameState(game));
    }
}
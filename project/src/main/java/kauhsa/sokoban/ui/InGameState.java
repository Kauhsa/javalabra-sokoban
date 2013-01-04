/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import kauhsa.sokoban.core.utils.Direction;
import kauhsa.sokoban.game.SokobanGame;
import kauhsa.sokoban.level.InvalidLevelException;
import kauhsa.sokoban.ui.world.WorldRenderer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

/**
 *
 * @author mika
 */
public class InGameState extends BasicGameState {

    private SokobanGame game = null;
    private WorldRenderer worldRenderer = null;

    public InGameState() {
        super();
    }

    @Override
    public int getID() {
        return GameStates.IN_GAME.ordinal();
    }

    public void loadGame(SokobanGame game) throws SlickException {
        this.game = game;
        this.worldRenderer = new WorldRenderer(game.getWorld());
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        if (game != null) {
            this.worldRenderer.render(0, 0, gc.getWidth(), gc.getHeight());
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (game == null) {
            return;
        }

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_UP)) {
            game.movePlayer(Direction.UP);
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            game.movePlayer(Direction.DOWN);
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            game.movePlayer(Direction.LEFT);
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            game.movePlayer(Direction.RIGHT);
        } else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(GameStates.LEVEL_MENU.ordinal());
        } else if (input.isKeyPressed(Input.KEY_R)) {
            restart();
        }
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    private void restart() throws SlickException {
        try {
            loadGame(new SokobanGame(game.getLevel()));
        } catch (InvalidLevelException ex) {
            Log.error("Could not restart level", ex);
        }
    }
}

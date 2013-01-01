/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui;

import kauhsa.sokoban.core.utils.Direction;
import kauhsa.sokoban.game.Game;
import kauhsa.sokoban.ui.world.WorldRenderer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author mika
 */
public class InGameState extends BasicGameState {
    private final Game game;
    private final WorldRenderer worldRenderer;
    
    public InGameState(Game game) {
        super();
        this.game = game;
        this.worldRenderer = new WorldRenderer(game.getWorld());
    }
    
    @Override
    public int getID() {
        return 1;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        this.worldRenderer.render(0, 0, gc.getWidth(), gc.getHeight());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {    
        Input input = gc.getInput();        
        
        if (input.isKeyPressed(Input.KEY_UP)) {
            game.movePlayer(Direction.UP);
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            game.movePlayer(Direction.DOWN);
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            game.movePlayer(Direction.LEFT);
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            game.movePlayer(Direction.RIGHT);
        }
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {    
    }
    
}

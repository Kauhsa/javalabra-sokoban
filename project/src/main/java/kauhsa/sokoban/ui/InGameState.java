/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.ui;

import kauhsa.sokoban.game.Game;
import kauhsa.sokoban.ui.world.WorldRenderer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        this.worldRenderer.render(0, 0, 800, 600);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
    }
    
}

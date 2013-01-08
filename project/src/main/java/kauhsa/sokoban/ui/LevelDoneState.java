package kauhsa.sokoban.ui;

import kauhsa.sokoban.resources.FontLoader;
import kauhsa.sokoban.ui.label.Label;
import kauhsa.sokoban.ui.utils.HorizontalAlignment;
import kauhsa.sokoban.ui.utils.VerticalAlignment;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * State that shows up when you finish a level.
 */
public class LevelDoneState extends BasicGameState {

    private Label congratulationLabel = new Label();
    
    @Override
    public int getID() {
        return GameStates.LEVEL_DONE.ordinal();
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        congratulationLabel.setFont(FontLoader.loadAwtFontToSlick("Ubuntu", java.awt.Font.PLAIN, 80));
        congratulationLabel.setText("Congratulations!");
        congratulationLabel.setHorizontalAlignment(HorizontalAlignment.MIDDLE);        
        congratulationLabel.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        // render the in game state in background
        sbg.getState(GameStates.IN_GAME.ordinal()).render(gc, sbg, grphcs);
        
        // draw a dark shade for the game in background
        grphcs.setColor(new Color(0, 0, 0, 210));
        grphcs.fillRect(0, 0, gc.getWidth(), gc.getHeight());
        
        congratulationLabel.render(grphcs, 0, 0, gc.getWidth(), gc.getHeight());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_ENTER)) {
            sbg.enterState(GameStates.LEVEL_MENU.ordinal());
        }
    }
    
}

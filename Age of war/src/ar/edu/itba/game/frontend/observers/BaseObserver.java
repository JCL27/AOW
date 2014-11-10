package ar.edu.itba.game.frontend.observers;

import ar.edu.itba.game.backend.logic.Base;
import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.frontend.drawableobjects.BaseDrawableObject;
import ar.edu.itba.game.frontend.userinterface.UIManager;

/**
 * Track the Bases HP and draws it
 *
 */
public class BaseObserver{	
	/**
	 * Creates draws for the bases and adds them to UIManager
	 */
	public BaseObserver(){
		UIManager.getInstance().setPlayerBase(new BaseDrawableObject(GameStats.BASE_PLAYER_X, Game.GROUND_HEIGHT, 
				GameStats.BASE_HEIGHT, GameStats.BASE_WIDTH, Side.LEFT, GameStats.BASE_MAX_HP));
		UIManager.getInstance().setAIBase(new BaseDrawableObject(GameStats.BASE_AI_X, Game.GROUND_HEIGHT, 
				GameStats.BASE_HEIGHT, GameStats.BASE_WIDTH, Side.RIGHT, GameStats.BASE_MAX_HP));
	}
	
	/**
	 * Updates the current HP to be displayed
	 * @param base
	 */
	public void update(Base base) {
		if(base.getSide().equals(Side.LEFT))
			UIManager.getInstance().getPlayerBase().setCurrent(base.getHP());
		else
			UIManager.getInstance().getAIBase().setCurrent(base.getHP());
	}		
	
	public void dispose() {
	}
	
	/**
	 * When the game ends, informs UIManager who is the looser
	 * @param side
	 */
	public void setLooser(Side side) {
		UIManager.getInstance().setLooser(side);
	}
	
}

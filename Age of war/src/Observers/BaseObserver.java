package Observers;

import DrawableObjects.BaseDrawableObject;
import UserInterface.UIManager;
import ar.edu.itba.game.Base;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Side;

public class BaseObserver{

	
	public BaseObserver(){
		UIManager.getInstance().setPlayerBase(new BaseDrawableObject(GameStats.BASE_PLAYER_X, Game.GROUND_HEIGHT, 
				GameStats.BASE_HEIGHT, GameStats.BASE_WIDTH, Side.LEFT, GameStats.BASE_MAX_HP));
		UIManager.getInstance().setAIBase(new BaseDrawableObject(GameStats.BASE_AI_X, Game.GROUND_HEIGHT, 
				GameStats.BASE_HEIGHT, GameStats.BASE_WIDTH, Side.RIGHT, GameStats.BASE_MAX_HP));
	}
	
	public void update(Base base) {
		if(base.getSide().equals(Side.LEFT))
			UIManager.getInstance().getPlayerBase().setCurrent(base.getHP());
		else
			UIManager.getInstance().getAIBase().setCurrent(base.getHP());
	}		
	
	public void dispose() {
	}
	
}

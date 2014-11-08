package Buttons;

import UserInterface.GameUIState;
import UserInterface.UIManager;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameState;
import Draws.LoadDraw;

public class LoadButton extends Button {

	public LoadButton(float X, float Y){
		this.draw = new LoadDraw(X, Y, 100, 300);
	}	
	
	@Override
	public void Click() {
		Game.loadGame();
		Game.gameState = GameState.GAME;
		UIManager.getInstance().pushState(GameUIState.DEFAULT);
		UIManager.getInstance().updateButtons();

	}

}

package Buttons;

import Draws.SaveDraw;
import UserInterface.GameUIState;
import UserInterface.UIManager;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameState;

public class SaveButton extends Button {

	public SaveButton(float X, float Y){
		this.draw = new SaveDraw(X, Y, 100, 300);
	}	
	
	@Override
	public void Click() {
		Game.saveGame();
		Game.gameState = GameState.GAME;
		UIManager.getInstance().pushState(GameUIState.DEFAULT);
		UIManager.getInstance().updateButtons();
	}

}

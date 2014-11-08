package Buttons;

import Draws.ContinueDraw;
import UserInterface.GameUIState;
import UserInterface.UIManager;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameState;

public class ContinueButton extends Button {

	public ContinueButton(float X, float Y){
		this.draw = new ContinueDraw(X, Y, 100, 300);
	}

	@Override
	public void Click() {
		Game.gameState = GameState.GAME;
		UIManager.getInstance().pushState(GameUIState.DEFAULT);
		UIManager.getInstance().updateButtons();
	}


}

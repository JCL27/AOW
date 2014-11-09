package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameState;
import ar.edu.itba.game.frontend.draws.ContinueDraw;
import ar.edu.itba.game.frontend.userinterface.GameUIState;
import ar.edu.itba.game.frontend.userinterface.UIManager;

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

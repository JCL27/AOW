package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameState;
import ar.edu.itba.game.frontend.draws.SaveDraw;
import ar.edu.itba.game.frontend.userinterface.GameUIState;
import ar.edu.itba.game.frontend.userinterface.UIManager;

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

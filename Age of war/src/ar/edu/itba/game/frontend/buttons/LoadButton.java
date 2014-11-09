package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameState;
import ar.edu.itba.game.frontend.draws.LoadDraw;
import ar.edu.itba.game.frontend.userinterface.GameUIState;
import ar.edu.itba.game.frontend.userinterface.UIManager;

public class LoadButton extends Button {

	public LoadButton(float X, float Y){
		this.draw = new LoadDraw(X, Y, 100, 300);
	}	
	
	@Override
	public void Click() {
		Game.loadGame();
		UIManager.getInstance().pushState(GameUIState.DEFAULT);
		UIManager.getInstance().updateButtons();

	}

}

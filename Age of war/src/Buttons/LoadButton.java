package Buttons;

import Draws.LoadDraw;
import UserInterface.GameUIState;
import UserInterface.UIManager;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameState;

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

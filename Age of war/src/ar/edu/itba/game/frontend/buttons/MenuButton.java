package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameState;
import ar.edu.itba.game.frontend.draws.MainButtonDraw;

public class MenuButton extends ar.edu.itba.game.frontend.buttons.Button {

	public MenuButton(float X, float Y){
		this.draw = new MainButtonDraw(X, Y, 50, 120);
	}	


	@Override
	public void Click() {
		Game.gameState = GameState.MENU;
	}

}

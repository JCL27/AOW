package Buttons;

import Draws.MainButtonDraw;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameState;

public class MenuButton extends Buttons.Button {

	public MenuButton(float X, float Y){
		this.draw = new MainButtonDraw(X, Y, 50, 120);
	}	


	@Override
	public void Click() {
		Game.gameState = GameState.MENU;
	}

}

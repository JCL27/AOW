package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.backend.logic.Game;


public class NewGameButton extends Button{

	
	public NewGameButton(float X, float Y){
		this.draw = new ar.edu.itba.game.frontend.draws.NewGameDraw(X, Y, 100, 300);
	}	
	
	@Override
	public void Click() {
		Game.newGame();
	}

}

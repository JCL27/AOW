package Buttons;

import ar.edu.itba.game.Game;


public class NewGameButton extends Button{

	
	public NewGameButton(float X, float Y){
		this.draw = new Draws.NewGameDraw(X, Y, 100, 300);
	}	
	
	@Override
	public void Click() {
		Game.newGame();
	}

}

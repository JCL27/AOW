package Buttons;

import Draws.MainButtonDraw;
import ar.edu.itba.game.Game;

public class MenuButton extends Buttons.Button {
	static boolean saved = false;
	
	public MenuButton(float X, float Y){
		this.draw = new MainButtonDraw(X, Y);
	}	
	
	
	@Override
	public void Click() {
		// TODO Auto-generated method stub
		if(saved == false){
			System.out.println("antes de guarda");
			Game.saveGame();
			saved = true;
			
		}else{
			Game.loadGame();
		}
		
	}

}

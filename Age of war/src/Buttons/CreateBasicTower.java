package Buttons;

import Draws.BigGreenButtonDraw;
import ar.edu.itba.game.WorldManager;

public class CreateBasicTower extends Button {
	
	public CreateBasicTower(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
		
	}

	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().buyTower();
		//WorldManager.getInstance().getplayerAI().CreateTower();
	}
	

}

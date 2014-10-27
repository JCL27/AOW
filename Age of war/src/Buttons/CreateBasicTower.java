package Buttons;

import Draws.BigGreenButtonDraw;
import ar.edu.itba.game.BasicTower;
import ar.edu.itba.game.WorldManager;

public class CreateBasicTower extends Button {
	
	public CreateBasicTower(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
		
	}

	@Override
	public void Click() {
		// TODO Auto-generated method stub
		WorldManager.getInstance().getPlayer().CreateTower(BasicTower.class);
	}
	

}

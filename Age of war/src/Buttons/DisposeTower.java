package Buttons;

import Draws.BigGreenButtonDraw;
import ar.edu.itba.game.WorldManager;

public class DisposeTower extends Button{
	
	public DisposeTower(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
		
	}
	
	@Override
	public void Click() {
		// TODO Auto-generated method stub
		WorldManager.getInstance().getPlayer().sellTower();
	}

}

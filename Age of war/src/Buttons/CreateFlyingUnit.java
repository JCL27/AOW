package Buttons;

import Draws.BigGreenButtonDraw;
import ar.edu.itba.game.FlyingUnit;
import ar.edu.itba.game.WorldManager;

public class CreateFlyingUnit extends Button {

	public CreateFlyingUnit(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}

	@Override
	public void Click() {
		// TODO Auto-generated method stub
		WorldManager.getInstance().getPlayer().createUnit(FlyingUnit.class);
	}

}

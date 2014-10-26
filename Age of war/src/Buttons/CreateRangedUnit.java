package Buttons;

import Draws.BigGreenButtonDraw;
import Units.RangedUnit;
import ar.edu.itba.game.*;

public class CreateRangedUnit extends Button {

	public CreateRangedUnit(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}

	@Override
	public void Click() {
		// TODO Auto-generated method stub
		System.out.println("creo melee");
		WorldManager.getInstance().getPlayer().createUnit(RangedUnit.class);
	}

}
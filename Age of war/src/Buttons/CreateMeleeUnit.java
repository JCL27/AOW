package Buttons;

import Draws.BigGreenButtonDraw;
import ar.edu.itba.game.*;

public class CreateMeleeUnit extends Button {

	public CreateMeleeUnit(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}

	@Override
	public void Click() {
		// TODO Auto-generated method stub
		System.out.println("creo melee");
		WorldManager.getInstance().getPlayer().createUnit(MeleeUnit.class);
	}

}

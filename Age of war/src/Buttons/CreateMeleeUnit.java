package Buttons;

import UserInterface.Textures;
import ar.edu.itba.game.*;

public class CreateMeleeUnit extends Button {

	public CreateMeleeUnit(float X, float Y) {
		super(Textures.BUTTON, X, Y, 3);
	}

	@Override
	public void Click() {
		// TODO Auto-generated method stub
		WorldManager.getInstance().getPlayer().createUnit();
	}

}
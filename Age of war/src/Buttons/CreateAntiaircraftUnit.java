package Buttons;

import Draws.BigGreenButtonDraw;
import Units.AntiaircraftUnit;
import Units.MeleeUnit;
import ar.edu.itba.game.WorldManager;

public class CreateAntiaircraftUnit extends Button {

	public CreateAntiaircraftUnit(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}

	@Override
	public void Click() {
		// TODO Auto-generated method stub
		//WorldManager.getInstance().getPlayer().createUnit(AntiaircraftUnit.class);
		WorldManager.getInstance().getPlayer().buyUnit(AntiaircraftUnit.class);
	}
}

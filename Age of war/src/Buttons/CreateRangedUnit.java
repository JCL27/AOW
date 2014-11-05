package Buttons;

import Draws.Icon;
import Draws.Textures;
import Units.RangedUnit;
import ar.edu.itba.game.WorldManager;

public class CreateRangedUnit extends Button {
	
	public CreateRangedUnit(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.RANGED_UNIT_ICON, Textures.DARK_RANGED_UNIT_ICON);
	}

	@Override
	public void Click() {
		// TODO Auto-generated method stub
		//WorldManager.getInstance().getPlayer().createUnit(RangedUnit.class);
		WorldManager.getInstance().getPlayer().buyUnit(RangedUnit.class);
	}

}

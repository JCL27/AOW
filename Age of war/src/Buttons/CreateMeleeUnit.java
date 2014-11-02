package Buttons;

import Draws.BigGreenButtonDraw;
import Units.MeleeUnit;
import ar.edu.itba.game.WorldManager;

public class CreateMeleeUnit extends Button{
	
		public CreateMeleeUnit(float X, float Y) {
			this.draw = new BigGreenButtonDraw(X, Y);
		}
	@Override
	public void Click() {
		// TODO Auto-generated method stub
		WorldManager.getInstance().getPlayer().createUnit(MeleeUnit.class);
		int foo;
	}
}

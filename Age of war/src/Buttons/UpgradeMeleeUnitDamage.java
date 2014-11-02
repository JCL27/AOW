package Buttons;

import Draws.BigGreenButtonDraw;
import Units.MeleeUnit;
import Upgrades.Upgrades;
import ar.edu.itba.game.WorldManager;

public class UpgradeMeleeUnitDamage extends Button{
	public UpgradeMeleeUnitDamage(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	public void Click(){
		Upgrades.getInstance().applyUpgrade("UnitUpgrade",WorldManager.getInstance().getPlayer(), MeleeUnit.class);
	}
}

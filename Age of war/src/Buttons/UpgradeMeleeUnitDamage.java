package Buttons;

import Draws.BigGreenButtonDraw;
import Upgrades.Upgrades;
import ar.edu.itba.game.WorldManager;

public class UpgradeMeleeUnitDamage extends Button{
	public UpgradeMeleeUnitDamage(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}

	public void Click(){
		Upgrades.getInstance().applyUpgrade("MeleeUnitUpgrade",(WorldManager.getInstance().getPlayer()));
	}
}

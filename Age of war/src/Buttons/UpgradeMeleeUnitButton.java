package Buttons;

import Draws.Icon;
import Draws.Textures;
import Units.MeleeUnit;
import Upgrades.UnitUpgrade;
import ar.edu.itba.game.WorldManager;

public class UpgradeMeleeUnitButton extends Button{
	public UpgradeMeleeUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.MELEE_UNIT_UPGRADE_ICON, Textures.DARK_MELEE_UNIT_UPGRADE_ICON);
	}
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(UnitUpgrade.class, MeleeUnit.class);
	}
}

package Buttons;

import Draws.Icon;
import Draws.Textures;
import Units.FlyingUnit;
import Upgrades.UnitUpgrade;
import ar.edu.itba.game.WorldManager;

public class UpgradeFlyingUnitButton  extends Button{
	public UpgradeFlyingUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.FLYING_UNIT_UPGRADE_ICON, Textures.DARK_FLYING_UNIT_UPGRADE_ICON);
	}
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(UnitUpgrade.class, FlyingUnit.class);
	}
}

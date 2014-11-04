package Buttons;

import Draws.BigGreenButtonDraw;
import Draws.Icon;
import Draws.Textures;
import Units.MeleeUnit;
import Upgrades.TowerDamageUpgrade;
import Upgrades.UnitUpgrade;
import ar.edu.itba.game.WorldManager;

public class UpgradeMeleeUnitButton extends Button{
	public UpgradeMeleeUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_DAMAGE_UPGRADE_ICON, Textures.DARK_TOWER_DAMAGE_UPGRADE_ICON);
	}
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(TowerDamageUpgrade.class);
	}
}

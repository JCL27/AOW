package Buttons;

import Draws.Icon;
import Draws.Textures;
import Upgrades.TowerDamageUpgrade;
import ar.edu.itba.game.WorldManager;

public class UpgradeAntiaircraftUnitButton extends Button {
	public UpgradeAntiaircraftUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_DAMAGE_UPGRADE_ICON, Textures.DARK_TOWER_DAMAGE_UPGRADE_ICON);
	}
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(TowerDamageUpgrade.class);
	}
}

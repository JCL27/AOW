package Buttons;

import Draws.Icon;
import Draws.Textures;
import Upgrades.TowerAttackRangeUpgrade;
import ar.edu.itba.game.WorldManager;

public class TowerAttackRangeUpgradeButton extends Button {
	
	public TowerAttackRangeUpgradeButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_RANGE_UPGRADE_ICON, Textures.DARK_TOWER_RANGE_UPGRADE_ICON);
	}
	
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(TowerAttackRangeUpgrade.class);
	}

}

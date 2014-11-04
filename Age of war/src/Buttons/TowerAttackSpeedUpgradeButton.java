package Buttons;

import Draws.BigGreenButtonDraw;
import Draws.Icon;
import Draws.Textures;
import Upgrades.TowerAttackSpeedUpgrade;
import ar.edu.itba.game.WorldManager;

public class TowerAttackSpeedUpgradeButton extends Button {
	
	public TowerAttackSpeedUpgradeButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_ATTACK_SPEED_UPGRADE_ICON, Textures.DARK_TOWER_ATTACK_SPEED_UPGRADE_ICON);
	}
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(TowerAttackSpeedUpgrade.class);
	}
}

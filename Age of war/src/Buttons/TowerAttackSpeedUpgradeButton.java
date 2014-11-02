package Buttons;

import Draws.BigGreenButtonDraw;
import Upgrades.TowerAttackSpeedUpgrade;
import ar.edu.itba.game.WorldManager;

public class TowerAttackSpeedUpgradeButton extends Button {
	
	public TowerAttackSpeedUpgradeButton(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(TowerAttackSpeedUpgrade.class);
	}
}

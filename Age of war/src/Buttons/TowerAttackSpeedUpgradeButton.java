package Buttons;

import Draws.BigGreenButtonDraw;
import Upgrades.Upgrades;
import ar.edu.itba.game.WorldManager;

public class TowerAttackSpeedUpgradeButton extends Button {
	
	public TowerAttackSpeedUpgradeButton(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	@Override
	public void Click() {
		Upgrades.getInstance().applyUpgrade("TowerAttackSpeedUpgrade", WorldManager.getInstance().getPlayer());
	}
}

package Buttons;

import Draws.BigGreenButtonDraw;
import Upgrades.Upgrades;
import ar.edu.itba.game.WorldManager;

public class TowerAttackRangeUpgradeButton extends Button {
	
	public TowerAttackRangeUpgradeButton(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	
	@Override
	public void Click() {
		Upgrades.getInstance().applyUpgrade("TowerAttackRangeUpgrade", WorldManager.getInstance().getPlayer());
	}

}

package Buttons;

import Draws.BigGreenButtonDraw;
import Upgrades.TowerAttackRangeUpgrade;
import ar.edu.itba.game.WorldManager;

public class TowerAttackRangeUpgradeButton extends Button {
	
	public TowerAttackRangeUpgradeButton(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(TowerAttackRangeUpgrade.class);
	}

}

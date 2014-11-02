package Buttons;

import Draws.BigGreenButtonDraw;
import Upgrades.TowerDamageUpgrade;
import ar.edu.itba.game.WorldManager;

public class TowerDamageUpgradeButton extends Button {
	
	public TowerDamageUpgradeButton(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(TowerDamageUpgrade.class);
	}
}

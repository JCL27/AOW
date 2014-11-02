package Buttons;

import Draws.BigGreenButtonDraw;
import Upgrades.Upgrades;
import ar.edu.itba.game.WorldManager;

public class TowerDamageUpgradeButton extends Button {
	
	public TowerDamageUpgradeButton(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	@Override
	public void Click() {
		Upgrades.getInstance().applyUpgrade("TowerDamageUpgrade", WorldManager.getInstance().getPlayer());
	}
}

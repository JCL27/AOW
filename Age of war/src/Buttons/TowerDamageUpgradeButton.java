package Buttons;

import Draws.Icon;
import Draws.Textures;
import Upgrades.TowerDamageUpgrade;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class TowerDamageUpgradeButton extends Button {

	public TowerDamageUpgradeButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_DAMAGE_UPGRADE_ICON, Textures.DARK_TOWER_DAMAGE_UPGRADE_ICON);
	}

	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if(player.getExp()<GameStats.TOWER_DAMAGE_UPGRADE_COST ||
				(player.getTower()==null) || player.getTower().isUpgradedDamage()){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}


	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(TowerDamageUpgrade.class);
	}
}

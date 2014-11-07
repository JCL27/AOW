package Buttons;

import Draws.Icon;
import Draws.Textures;
import Upgrades.TowerAttackRangeUpgrade;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class TowerAttackRangeUpgradeButton extends Button {
	
	public TowerAttackRangeUpgradeButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_RANGE_UPGRADE_ICON, Textures.DARK_TOWER_RANGE_UPGRADE_ICON);
	}
	
	
	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if(player.getExp()<GameStats.TOWER_ATTACK_RANGE_UPGRADE_COST ||
				(player.getTower()==null) || player.getTower().isUpgradedAttackRange()){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}


	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(TowerAttackRangeUpgrade.class);
	}

}

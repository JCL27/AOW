package Upgrades;

import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import exceptions.AlreadyAppliedUpgradeException;

public class TowerAttackRangeUpgrade extends Upgrade {
	
	public TowerAttackRangeUpgrade(Player player) {
		super(player);
		this.cost = GameStats.TOWER_ATTACK_RANGE_UPGRADE_COST;
	}

	@Override
	public void applyUpgrade(Class classType) {
		try {
			player.getTower().upgradeAttackRange();
		} catch (AlreadyAppliedUpgradeException e) {
			e.printStackTrace();
		}
	}

}

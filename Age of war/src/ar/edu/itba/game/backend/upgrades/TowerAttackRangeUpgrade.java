package ar.edu.itba.game.backend.upgrades;

import ar.edu.itba.game.backend.exceptions.AlreadyAppliedUpgradeException;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.units.UnitType;

public class TowerAttackRangeUpgrade extends Upgrade {
	
	public TowerAttackRangeUpgrade(Player player) {
		super(player);
		this.cost = GameStats.TOWER_ATTACK_RANGE_UPGRADE_COST;
	}
	
	/**
	 * Upgrades the tower's attack range
	 */
	
	@Override
	public void applyUpgrade(UnitType type) {
		try {
			player.getTower().upgradeAttackRange();
		} catch (AlreadyAppliedUpgradeException e) {
			e.printStackTrace();
		}
	}

}

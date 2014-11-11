package ar.edu.itba.game.backend.upgrades;

import ar.edu.itba.game.backend.exceptions.AlreadyAppliedUpgradeException;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.units.UnitType;

public class TowerAttackSpeedUpgrade extends Upgrade {

	public TowerAttackSpeedUpgrade(Player player) {
		super(player);
		this.cost = GameStats.TOWER_ATTACK_SPEED_UPGRADE_COST;
	}
	
	/**
	 * Upgrades the tower's attack speed
	 */
	
	@Override
	public void applyUpgrade(UnitType type) {
		try {
			player.getTower().upgradeAttackSpeed();
		} catch (AlreadyAppliedUpgradeException e) {
			e.printStackTrace();
		}
	}

}

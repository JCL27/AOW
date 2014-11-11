package ar.edu.itba.game.backend.upgrades;

import ar.edu.itba.game.backend.exceptions.AlreadyAppliedUpgradeException;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.units.UnitType;

public class TowerDamageUpgrade extends Upgrade {

	public TowerDamageUpgrade(Player player) {
		super(player);
		this.cost = GameStats.TOWER_DAMAGE_UPGRADE_COST;
	}
	
	/**
	 * Upgrades the tower's damage
	 */
	
	@Override
	public void applyUpgrade(UnitType type) {
		try {
			player.getTower().upgradeDamage();
		} catch (AlreadyAppliedUpgradeException e) {
			e.printStackTrace();
		}
	}

}

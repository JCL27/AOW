package Upgrades;

import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import exceptions.AlreadyAppliedUpgradeException;

public class TowerDamageUpgrade extends Upgrade {

	public TowerDamageUpgrade(Player player) {
		super(player);
		this.cost = GameStats.TOWER_DAMAGE_UPGRADE_COST;
	}

	@Override
	public void applyUpgrade(Class classType) {
		try {
			player.getTower().upgradeDamage();
		} catch (AlreadyAppliedUpgradeException e) {
			e.printStackTrace();
		}
	}

}

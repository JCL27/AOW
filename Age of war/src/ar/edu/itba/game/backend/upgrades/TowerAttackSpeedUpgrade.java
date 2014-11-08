package Upgrades;

import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import exceptions.AlreadyAppliedUpgradeException;

public class TowerAttackSpeedUpgrade extends Upgrade {

	public TowerAttackSpeedUpgrade(Player player) {
		super(player);
		this.cost = GameStats.TOWER_ATTACK_SPEED_UPGRADE_COST;
	}

	@Override
	public void applyUpgrade(Class classType) {
		try {
			player.getTower().upgradeAttackSpeed();
		} catch (AlreadyAppliedUpgradeException e) {
			e.printStackTrace();
		}
	}

}

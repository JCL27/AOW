package Upgrades;

import ar.edu.itba.game.Player;
import exceptions.AlreadyAppliedUpgradeException;

public class TowerAttackSpeedUpgrade extends Upgrade {

	public TowerAttackSpeedUpgrade(Player player) {
		super(player);
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

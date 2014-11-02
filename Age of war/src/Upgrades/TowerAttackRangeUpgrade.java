package Upgrades;

import ar.edu.itba.game.Player;
import exceptions.AlreadyAppliedUpgradeException;

public class TowerAttackRangeUpgrade extends Upgrade {
	
	public TowerAttackRangeUpgrade(Player player) {
		super(player);
		this.cost = 60;
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

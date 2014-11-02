package Upgrades;

import ar.edu.itba.game.Player;
import exceptions.AlreadyAppliedUpgradeException;

public class TowerDamageUpgrade extends Upgrade {

	public TowerDamageUpgrade(Player player) {
		super(player);
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

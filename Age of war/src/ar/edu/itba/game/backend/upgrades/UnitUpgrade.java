package ar.edu.itba.game.backend.upgrades;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.units.UnitType;
import ar.edu.itba.game.backend.units.UnitsLevels;

public class UnitUpgrade extends Upgrade {
	
	public UnitUpgrade(Player player) {
		super(player);
		this.available = true;
		this.cost = GameStats.UNIT_UPGRADE_COST;
		this.multiUpgradable = true;
	}
	
	/**
	 * Upgrades the unit specified by "classType", adding 1 to the current level, afecting the
	 * multiplier of its upgrade rate
	 */

	@Override
	public void applyUpgrade(UnitType type) {
		UnitsLevels.getInstance().levelUp(this.player, type);
	}
	

}

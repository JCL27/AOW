package ar.edu.itba.game.backend.upgrades;

import java.lang.reflect.InvocationTargetException;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.Unit;

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
	public void applyUpgrade(Class<Unit> classType) {
		try {
			if(this.player.equals(WorldManager.getInstance().getPlayer())){
				classType.getMethod("playerLevelUp").invoke(classType);
			}else{
				classType.getMethod("AILevelUp").invoke(classType);
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}
	

}

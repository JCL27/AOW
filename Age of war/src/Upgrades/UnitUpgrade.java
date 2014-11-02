package Upgrades;

import java.lang.reflect.InvocationTargetException;

import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class UnitUpgrade extends Upgrade {
	
	public UnitUpgrade(Player player) {
		super(player);
		this.available = true;
		this.cost = GameStats.UNIT_UPGRADE_COST;
	}

	@Override
	public void applyUpgrade(Class classType) {
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

package Upgrades;

import java.lang.reflect.InvocationTargetException;

import Units.MeleeUnit;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class UnitUpgrade extends Upgrade {
	
	public UnitUpgrade(Player player) {
		super(player);
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

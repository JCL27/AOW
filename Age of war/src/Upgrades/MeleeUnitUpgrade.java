package Upgrades;

import Units.RangedUnit;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class MeleeUnitUpgrade extends Upgrade {

	@Override
	public void applyUpgrade(Player player) {
		if(player.equals(WorldManager.getInstance().getPlayer())){
			RangedUnit.playerLevelUp();
		}else{
			RangedUnit.AILevelUp();
		}
	}

}

package Upgrades;

import Units.FlyingUnit;
import Units.Unit;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class FlyingUnitResearch extends Upgrade {

	public FlyingUnitResearch(Player player) {
		super(player);
		this.cost = GameStats.FLYING_UNIT_RESEARCH_COST;
		this.available = true;
		this.multiUpgradable = false;
	}

	@Override
	public void applyUpgrade(Class<Unit> classType) {
		if(this.player.equals(WorldManager.getInstance().getPlayer()))
			FlyingUnit.setPlayerAvailable(true);
		else
			FlyingUnit.setAIAvailable(true);
	}

}

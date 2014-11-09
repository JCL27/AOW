package ar.edu.itba.game.backend.upgrades;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.FlyingUnit;
import ar.edu.itba.game.backend.units.Unit;

public class FlyingUnitResearch extends Upgrade {

	public FlyingUnitResearch(Player player) {
		super(player);
		this.cost = GameStats.FLYING_UNIT_RESEARCH_COST;
		this.available = true;
		this.multiUpgradable = false;
	}
	
	/**
	 * Enables the player to buy flying units
	 */
	
	@Override
	public void applyUpgrade(Class<Unit> classType) {
		if(this.player.equals(WorldManager.getInstance().getPlayer()))
			FlyingUnit.setPlayerAvailable(true);
		else
			FlyingUnit.setAIAvailable(true);
	}

}

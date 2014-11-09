package ar.edu.itba.game.backend.upgrades;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.AntiaircraftUnit;
import ar.edu.itba.game.backend.units.Unit;

public class AntiaircraftUnitResearch extends Upgrade {

	public AntiaircraftUnitResearch(Player player) {
		super(player);
		this.cost = GameStats.ANTIAIRCRAFT_UNIT_RESEARCH_COST;
		this.available = true;
		this.multiUpgradable = false;
	}

	/**
	 * Enables the player to buy antiaircraft units
	 */
	@Override
	public void applyUpgrade(Class<Unit> classType) {
		if(this.player.equals(WorldManager.getInstance().getPlayer()))
			AntiaircraftUnit.setPlayerAvailable(true);
		else
			AntiaircraftUnit.setAIAvailable(true);
	}

}

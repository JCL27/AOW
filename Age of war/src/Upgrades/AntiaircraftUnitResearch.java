package Upgrades;

import Units.AntiaircraftUnit;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class AntiaircraftUnitResearch extends Upgrade {

	public AntiaircraftUnitResearch(Player player) {
		super(player);
		this.cost = GameStats.ANTIAIRCRAFT_UNIT_RESEARCH_COST;
		this.available = true;
		this.multiUpgradable = false;
	}

	@Override
	public void applyUpgrade(Class classType) {
		if(this.player.equals(WorldManager.getInstance().getPlayer()))
			AntiaircraftUnit.setPlayerAvailable(true);
		else
			AntiaircraftUnit.setAIAvailable(true);
	}

}

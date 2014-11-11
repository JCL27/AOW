package ar.edu.itba.game.backend.units;

import java.io.Serializable;
import java.util.HashMap;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;


/**
 * Contains the level for the units classes
 * (The level for a given unit class is shared for all instances of that class)
 */
public class UnitsLevels implements Serializable{
	
	private static UnitsLevels instance;
	private static final long serialVersionUID = -3119219853274042761L;
	
	private HashMap<UnitType, Integer> playerLevels = new HashMap<UnitType, Integer>();
	private HashMap<UnitType, Integer> AILevels = new HashMap<UnitType, Integer>();
	
	private boolean playerFyingUnitAvailable = false;
	private boolean AIFyingUnitAvailable = false;
	private boolean playerAntiaircraftUnitAvailable = false;
	private boolean AIAntiaircraftUnitAvailable = false;
	
	public static void setInstance(UnitsLevels levels){
		instance = levels;
	}
	
	public boolean isPlayerFlyingUnitAvailable() {
		return playerFyingUnitAvailable;
	}

	public void setPlayerFlyingUnitAvailable(boolean playerFyingUnitAvailable) {
		this.playerFyingUnitAvailable = playerFyingUnitAvailable;
	}

	public boolean isAIFlyingUnitAvailable() {
		return AIFyingUnitAvailable;
	}

	public void setAIFlyingUnitAvailable(boolean aIFyingUnitAvailable) {
		AIFyingUnitAvailable = aIFyingUnitAvailable;
	}

	public boolean isPlayerAntiaircraftUnitAvailable() {
		return playerAntiaircraftUnitAvailable;
	}

	public void setPlayerAntiaircraftUnitAvailable(
			boolean playerAntiaircraftUnitAvailable) {
		this.playerAntiaircraftUnitAvailable = playerAntiaircraftUnitAvailable;
	}

	public boolean isAIAntiaircraftUnitAvailable() {
		return AIAntiaircraftUnitAvailable;
	}

	public void setAIAntiaircraftUnitAvailable(boolean aIAntiaircraftUnitAvailable) {
		AIAntiaircraftUnitAvailable = aIAntiaircraftUnitAvailable;
	}
	
	private UnitsLevels() {
		initializeLevels();
	}
	
	public static UnitsLevels getInstance(){
		if(instance==null){
			instance = new UnitsLevels();
		}
		return instance;
	}
	
	public void initializeLevels(){
		playerLevels.clear();
		AILevels.clear();
		for(UnitType unit: UnitType.values()){
			playerLevels.put(unit, 0);
			AILevels.put(unit, 0);
		}
		this.playerFyingUnitAvailable = false;
		this.AIFyingUnitAvailable = false;
		this.playerAntiaircraftUnitAvailable = false;
		this.AIAntiaircraftUnitAvailable = false;
	}
	
	public Integer getLevel(Player player, UnitType type){
		if(player.equals(WorldManager.getInstance().getPlayer()))
			return playerLevels.get(type);
		return AILevels.get(type);
	}

	
	public void levelUp(Player player, UnitType type){
		if(player.equals(WorldManager.getInstance().getPlayer()))
			playerLevels.put(type, playerLevels.get(type) + 1);
		else
			AILevels.put(type, AILevels.get(type) + 1);
	}
	
	public int getCost(Player player, UnitType type){
		HashMap<UnitType, Integer> levels;
		if(player.equals(WorldManager.getInstance().getPlayer()))
			levels=this.playerLevels;
		else
			levels=this.AILevels;
		switch(type){
		case MELEE_UNIT:
			return (int) (GameStats.MELEE_UNIT_COST + 
						Math.sqrt(levels.get(type) * GameStats.MELEE_UNIT_COST_UPGRADE_RATE));
		case RANGED_UNIT:
			return (int) (GameStats.RANGED_UNIT_COST + 
					Math.sqrt(levels.get(type) * GameStats.RANGED_UNIT_COST_UPGRADE_RATE));
		case ANTIAIRCRAFT_UNIT:
			return (int) (GameStats.ANTIAIRCRAFT_UNIT_COST + 
					Math.sqrt(levels.get(type) * GameStats.ANTIAIRCRAFT_UNIT_COST_UPGRADE_RATE));
		case FLYING_UNIT:
			return (int) (GameStats.FLYING_UNIT_COST + 
					Math.sqrt(levels.get(type) * GameStats.FLYING_UNIT_COST_UPGRADE_RATE));
		}
		return -1;
	}	
	
}

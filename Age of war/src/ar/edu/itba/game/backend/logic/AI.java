package ar.edu.itba.game.backend.logic;

import java.util.Random;

import ar.edu.itba.game.backend.units.UnitType;
import ar.edu.itba.game.backend.units.UnitsLevels;
import ar.edu.itba.game.backend.upgrades.UpgradeType;

public class AI {
	private static AI instance = null;

	private Player player;
	private boolean objectiveAccomplished = true;
	private boolean upgradeResearched = true;

	private Random rand = new Random();
	private int choice;
	private int upgradeChoice;

	private AI() {
		this.player = WorldManager.getInstance().getPlayerAI();
	}

	public static AI getInstance() {
		if(instance == null) {
			instance = new AI();
		}
		return instance;
	}

	/**
	 * Decides which unit to buy and which upgrade to research
	 * When it has less than two units in queue or on the field, it creates a melee and a ranged unit
	 * after that, it has a random chance to create any unit, with a higher chance of creating a melee or a ranged 
	 */
	public void desitionMaker(){

		if((player.getUnits().size() + player.getUnitsQueue().size()) < 2){
			if(player.getGold()>UnitsLevels.getInstance().getCost(player, UnitType.MELEE_UNIT)){
				player.buyUnit(UnitType.MELEE_UNIT);
			}
			if(player.getGold()>UnitsLevels.getInstance().getCost(player, UnitType.RANGED_UNIT)){
				player.buyUnit(UnitType.RANGED_UNIT);
			}	
		}else{
			if(objectiveAccomplished == true){
				choice = rand.nextInt(9);
				this.objectiveAccomplished = false;
			}
			switch(choice){
			case(0):
			case(1):
			case(2):
				if(player.getGold()>UnitsLevels.getInstance().getCost(player, UnitType.MELEE_UNIT)){
					player.buyUnit(UnitType.MELEE_UNIT);
					objectiveAccomplished = true;
				}	
			break;
			case(3):
			case(4):
			case(5):
				if(player.getGold()>UnitsLevels.getInstance().getCost(player, UnitType.RANGED_UNIT)){
					player.buyUnit(UnitType.RANGED_UNIT);
					objectiveAccomplished = true;
				}	
			break;
			case(6):
				if(UnitsLevels.getInstance().isAIAntiaircraftUnitAvailable()){
					if(player.getGold()>UnitsLevels.getInstance().getCost(player, UnitType.ANTIAIRCRAFT_UNIT)){
						player.buyUnit(UnitType.ANTIAIRCRAFT_UNIT);
						objectiveAccomplished = true;
					}
				}else{
					objectiveAccomplished = true;
				}
			break;
			case(7):
				if(UnitsLevels.getInstance().isAIFlyingUnitAvailable()){
					if(player.getGold()>UnitsLevels.getInstance().getCost(player, UnitType.FLYING_UNIT)){
						player.buyUnit(UnitType.FLYING_UNIT);
						objectiveAccomplished = true;
					}
				}else{
					objectiveAccomplished = true;
				}
			break;
			case(8):
				if(player.getTower()==null){
					if(player.getGold()>GameStats.TOWER_COST){
						player.buyTower();
						objectiveAccomplished = true;
					}
				}else{
					objectiveAccomplished = true;
				}
			break;
			}
		}
		if(upgradeResearched == true){
			upgradeChoice = rand.nextInt(9);
			this.upgradeResearched = false;
		}
		switch(upgradeChoice){
		case(0):
			if(player.getExp()> GameStats.UNIT_UPGRADE_COST){
				player.research(UpgradeType.UNIT_UPGRADE, UnitType.MELEE_UNIT);
				upgradeResearched = true;
			}	
		break;
		case(1):
			if(player.getExp()> GameStats.UNIT_UPGRADE_COST){
				player.research(UpgradeType.UNIT_UPGRADE, UnitType.RANGED_UNIT);
				upgradeResearched = true;
			}	
		break;
		case(2):
			if(UnitsLevels.getInstance().isAIFlyingUnitAvailable()){
				if(player.getExp()> GameStats.UNIT_UPGRADE_COST){
					player.research(UpgradeType.UNIT_UPGRADE, UnitType.FLYING_UNIT);
					upgradeResearched = true;
				}
			}else{
				upgradeResearched = true;
			}
		break;

		case(3):
			if(UnitsLevels.getInstance().isAIAntiaircraftUnitAvailable()){
				if(player.getExp()> GameStats.UNIT_UPGRADE_COST){
					player.research(UpgradeType.UNIT_UPGRADE, UnitType.ANTIAIRCRAFT_UNIT);
					upgradeResearched = true;
				}
			}else{
				upgradeResearched = true;
			}	
		break;
		case(4):
			if(!UnitsLevels.getInstance().isAIAntiaircraftUnitAvailable()){
				if(ar.edu.itba.game.backend.upgrades.Upgrades.getInstance().isAvailable(UpgradeType.ANTIAIRCRAFT_UNIT_RESEARCH, this.player)){
					if(player.getExp()> GameStats.ANTIAIRCRAFT_UNIT_RESEARCH_COST){
						player.research(UpgradeType.ANTIAIRCRAFT_UNIT_RESEARCH);
						upgradeResearched = true;
					}
				}else{
					upgradeResearched = true;
				}
			}else{
				upgradeResearched = true;
			}
		break;
		case(5):
			if(!UnitsLevels.getInstance().isAIFlyingUnitAvailable()){
				if(ar.edu.itba.game.backend.upgrades.Upgrades.getInstance().isAvailable(UpgradeType.FLYING_UNIT_RESEARCH, this.player)){
					if(player.getExp()> GameStats.FLYING_UNIT_RESEARCH_COST){
						player.research(UpgradeType.FLYING_UNIT_RESEARCH);
						upgradeResearched = true;
					}
				}else{
					upgradeResearched = true;
				}
			}else{
				upgradeResearched = true;
			}
		break;
		case(6):
			if(player.getTower()!=null){
				if(ar.edu.itba.game.backend.upgrades.Upgrades.getInstance().isAvailable(UpgradeType.TOWER_DAMAGE_UPGRADE, this.player)){
					if(player.getExp()> GameStats.TOWER_DAMAGE_UPGRADE_COST){
						player.research(UpgradeType.TOWER_DAMAGE_UPGRADE);
						upgradeResearched = true;
					}
				}else{
					upgradeResearched = true;
				}
			}else{
				upgradeResearched = true;
			}
		break;
		case(7):
			if(player.getTower()!=null){
				if(ar.edu.itba.game.backend.upgrades.Upgrades.getInstance().isAvailable(UpgradeType.TOWER_ATTACK_SPEED_UPGRADE, this.player)){
					if(player.getExp()> GameStats.TOWER_ATTACK_SPEED_UPGRADE_COST){
						player.research(UpgradeType.TOWER_ATTACK_SPEED_UPGRADE);
						upgradeResearched = true;
					}
				}else{
					upgradeResearched = true;
				}
			}else{
				upgradeResearched = true;
			}
		break;
		case(8):
			if(player.getTower()!=null){
				if(ar.edu.itba.game.backend.upgrades.Upgrades.getInstance().isAvailable(UpgradeType.TOWER_ATTACK_RANGE_UPGRADE, this.player)){
					if(player.getExp()> GameStats.TOWER_ATTACK_RANGE_UPGRADE_COST){
						player.research(UpgradeType.TOWER_ATTACK_RANGE_UPGRADE);
						upgradeResearched = true;
					}
				}else{
					upgradeResearched = true;
				}
			}else{
				upgradeResearched = true;
			}
		break;

		}

	}

	public static void reset() {
		instance = null;
	}
}

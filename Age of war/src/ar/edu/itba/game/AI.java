package ar.edu.itba.game;

import java.util.Random;

import Units.AntiaircraftUnit;
import Units.FlyingUnit;
import Units.MeleeUnit;
import Units.RangedUnit;
import Upgrades.AntiaircraftUnitResearch;
import Upgrades.FlyingUnitResearch;
import Upgrades.TowerAttackRangeUpgrade;
import Upgrades.TowerAttackSpeedUpgrade;
import Upgrades.TowerDamageUpgrade;
import Upgrades.UnitUpgrade;

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
	
	public void updateAI(){
		
	}
	
	public void desitionMaker(){
	
		if((player.getUnits().size() + player.getUnitsQueue().size()) < 2){
			player.buyUnit(Units.MeleeUnit.class);
			player.buyUnit(Units.RangedUnit.class);
		}else{
			if(objectiveAccomplished == true){
				choice = rand.nextInt(9);
				this.objectiveAccomplished = false;
			}
			switch(choice){
			case(0):
			case(1):
			case(2):
				if(player.getGold()>MeleeUnit.getCost(player)){
					player.buyUnit(MeleeUnit.class);
					objectiveAccomplished = true;
				}	
				break;
			case(3):
			case(4):
			case(5):
				if(player.getGold()>RangedUnit.getCost(player)){
					player.buyUnit(RangedUnit.class);
					objectiveAccomplished = true;
				}	
				break;
			case(6):
				if(AntiaircraftUnit.isAIAvailable()){
					if(player.getGold()>AntiaircraftUnit.getCost(player)){
						player.buyUnit(AntiaircraftUnit.class);
						objectiveAccomplished = true;
					}
				}else{
					objectiveAccomplished = true;
				}
				break;
			case(7):
				if(FlyingUnit.isAIAvailable()){
					if(player.getGold()>FlyingUnit.getCost(player)){
						player.buyUnit(FlyingUnit.class);
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
				player.research(UnitUpgrade.class, MeleeUnit.class);
				upgradeResearched = true;
			}	
			break;
		case(1):
			if(player.getExp()> GameStats.UNIT_UPGRADE_COST){
				player.research(UnitUpgrade.class, RangedUnit.class);
				upgradeResearched = true;
			}	
			break;
		case(2):
			if(FlyingUnit.isAIAvailable()){
				if(player.getExp()> GameStats.UNIT_UPGRADE_COST){
					player.research(UnitUpgrade.class, FlyingUnit.class);
					upgradeResearched = true;
				}
			}else{
				upgradeResearched = true;
			}
			break;
			
		case(3):
			if(AntiaircraftUnit.isAIAvailable()){
				if(player.getExp()> GameStats.UNIT_UPGRADE_COST){
					player.research(UnitUpgrade.class, AntiaircraftUnit.class);
					upgradeResearched = true;
				}
			}else{
				upgradeResearched = true;
			}	
			break;
		case(4):
			if(!AntiaircraftUnit.isAIAvailable()){
				if(Upgrades.Upgrades.getInstance().isAvailable(AntiaircraftUnitResearch.class, this.player)){
					if(player.getExp()> GameStats.ANTIAIRCRAFT_UNIT_RESEARCH_COST){
						player.research(AntiaircraftUnitResearch.class);
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
			if(!FlyingUnit.isAIAvailable()){
				if(Upgrades.Upgrades.getInstance().isAvailable(FlyingUnitResearch.class, this.player)){
					if(player.getExp()> GameStats.FLYING_UNIT_RESEARCH_COST){
						player.research(FlyingUnitResearch.class);
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
				if(Upgrades.Upgrades.getInstance().isAvailable(TowerDamageUpgrade.class, this.player)){
					if(player.getExp()> GameStats.TOWER_DAMAGE_UPGRADE_COST){
						player.research(TowerDamageUpgrade.class);
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
				if(Upgrades.Upgrades.getInstance().isAvailable(TowerAttackSpeedUpgrade.class, this.player)){
					if(player.getExp()> GameStats.TOWER_ATTACK_SPEED_UPGRADE_COST){
						player.research(TowerAttackSpeedUpgrade.class);
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
				if(Upgrades.Upgrades.getInstance().isAvailable(TowerAttackRangeUpgrade.class, this.player)){
					if(player.getExp()> GameStats.TOWER_ATTACK_RANGE_UPGRADE_COST){
						player.research(TowerAttackRangeUpgrade.class);
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
}

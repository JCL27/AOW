package ar.edu.itba.game;

import java.util.Random;

import Units.AntiaircraftUnit;
import Units.FlyingUnit;
import Units.MeleeUnit;
import Units.RangedUnit;
import Upgrades.UnitUpgrade;

public class AI {
	private static AI instance = null;
	
	private final int MELEE_UNIT = 5;
	private final int RANGED_UNIT = 5;
	private final int FLYING_UNIT = 5;
	private final int ANTIAIRCRAFT_UNIT = 5;
	private final int UPGRADE = 5;
	private Player player;
	private boolean objectiveAccomplished = true;
	private boolean upgradeResearched = true;
	
	private Random rand = new Random();
	private int choice;
	private int upgradeChoice;
	
	private AI() {
		this.player = WorldManager.getInstance().getplayerAI();
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
			System.out.println("unidades: " + player.getUnits().size());
			player.buyUnit(Units.MeleeUnit.class);
			player.buyUnit(Units.RangedUnit.class);
		}else{
			if(objectiveAccomplished == true){
				choice = rand.nextInt(8);
				this.objectiveAccomplished = false;
				//System.out.println("choice setted: " + choice);
			}
			//System.out.println("choice: " + choice + " gold: " + player.getGold());
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
				if(FlyingUnit.isAIAvailable()){
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
				if(player.getGold()>GameStats.TOWER_COST){
					player.buyUnit(FlyingUnit.class);
					objectiveAccomplished = true;
				}	
				break;
			}
		}
		
	/*	if(upgradeResearched == true){
			upgradeChoice = rand.nextInt(8);
			this.upgradeResearched = false;
			//System.out.println("choice setted: " + choice);
		}
		//System.out.println("choice: " + choice + " gold: " + player.getGold());
		switch(choice){
		case(0):
		case(1):
		case(2):
			if(player.getExp()> 5){
				player.createUnit(MeleeUnit.class);
				upgradeResearched = true;
			}	
			break;
		case(3):
		case(4):
		case(5):
		}
		*/
	}
}

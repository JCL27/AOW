package ar.edu.itba.game;

public class GameStats {
	//RANGED Unit Stats
	
		public final static Type RANGED_UNIT_TYPE = Type.GROUND;
		public final static boolean RANGED_UNIT_ATTACK_FLYING = false;
		public final static int RANGED_UNIT_MAX_HP = 300;
		public final static float RANGED_UNIT_ATTACK_SPEED = 15.0f;
		public final static int RANGED_UNIT_ATTACK_RANGE = 300;
		public final static int RANGED_UNIT_MOVEMENT_SPEED = 4;
		public final static int RANGED_UNIT_DAMAGE = 80;
		public final static int RANGED_UNIT_BOUNTY = 300;
		public final static int RANGED_UNIT_COST = 250;
		public final static int RANGED_UNIT_EXP = 70;
		public final static int RANGED_UNIT_CREATION_TIME = 50;
		
		public final static int RANGED_UNIT_MAX_HP_UPGRADE_RATE = 1;
		public final static double RANGED_UNIT_ATTACK_SPEED_UPGRADE_RATE = 0.5;
		public final static int RANGED_UNIT_ATTACK_RANGE_UPGRADE_RATE = 5;
		public final static int RANGED_UNIT_MOVEMENT_SPEED_UPGRADE_RATE = 0;
		public final static int RANGED_UNIT_DAMAGE_UPGRADE_RATE = 10;
		public final static int RANGED_UNIT_BOUNTY_UPGRADE_RATE = 10;
		public final static int RANGED_UNIT_COST_UPGRADE_RATE = 7;
		
		public final static int RANGED_UNIT_COL_BOX_HEIGHT = 50;
		public final static int RANGED_UNIT_COL_BOX_WIDTH = 50;
	
	//Flying Unit
		public final static Type FLYING_UNIT_TYPE = Type.FLYING;
		public final static boolean FLYING_UNIT_ATTACK_FLYING = false;
		public final static int FLYING_UNIT_MAX_HP = 300;
		public final static float FLYING_UNIT_ATTACK_SPEED = 5.0f;
		public final static int FLYING_UNIT_ATTACK_RANGE = 300;
		public final static int FLYING_UNIT_MOVEMENT_SPEED = 4;
		public final static int FLYING_UNIT_DAMAGE = 80;
		public final static int FLYING_UNIT_BOUNTY = 300;
		public final static int FLYING_UNIT_COST = 250;
		public final static int FLYING_UNIT_EXP = 70;
		public final static int FLYING_UNIT_CREATION_TIME = 100;
		
		public final static int FLYING_UNIT_MAX_HP_UPGRADE_RATE = 1;
		public final static double FLYING_UNIT_ATTACK_SPEED_UPGRADE_RATE = 0.1;
		public final static int FLYING_UNIT_ATTACK_RANGE_UPGRADE_RATE = 0;
		public final static int FLYING_UNIT_MOVEMENT_SPEED_UPGRADE_RATE = 1;
		public final static int FLYING_UNIT_DAMAGE_UPGRADE_RATE = 5;
		public final static int FLYING_UNIT_BOUNTY_UPGRADE_RATE = 10;
		public final static int FLYING_UNIT_COST_UPGRADE_RATE = 7;
		
		public final static int FLYING_UNIT_COL_BOX_HEIGHT = 150;
		public final static int FLYING_UNIT_COL_BOX_WIDTH = 100;
	
	//ANTIAIRCRAFT UNIT
		public final static Type ANTIAIRCRAFT_UNIT_TYPE = Type.GROUND;
		public final static boolean ANTIAIRCRAFT_UNIT_ATTACK_FLYING = true;
		public final static int ANTIAIRCRAFT_UNIT_MAX_HP = 300;
		public final static float ANTIAIRCRAFT_UNIT_ATTACK_SPEED = 5.0f;
		public final static int ANTIAIRCRAFT_UNIT_ATTACK_RANGE = 300;
		public final static int ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED = 4;
		public final static int ANTIAIRCRAFT_UNIT_DAMAGE = 80;
		public final static int ANTIAIRCRAFT_UNIT_BOUNTY = 300;
		public final static int ANTIAIRCRAFT_UNIT_COST = 250;
		public final static int ANTIAIRCRAFT_UNIT_EXP = 70;
		public final static int ANTIAIRCRAFT_UNIT_CREATION_TIME = 150;
		
		public final static int ANTIAIRCRAFT_UNIT_MAX_HP_UPGRADE_RATE = 1;
		public final static double ANTIAIRCRAFT_UNIT_ATTACK_SPEED_UPGRADE_RATE = 0.1;
		public final static int ANTIAIRCRAFT_UNIT_ATTACK_RANGE_UPGRADE_RATE = 0;
		public final static int ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED_UPGRADE_RATE = 1;
		public final static int ANTIAIRCRAFT_UNIT_DAMAGE_UPGRADE_RATE = 5;
		public final static int ANTIAIRCRAFT_UNIT_BOUNTY_UPGRADE_RATE = 10;
		public final static int ANTIAIRCRAFT_UNIT_COST_UPGRADE_RATE = 7;
		
		public final static int ANTIAIRCRAFT_UNIT_COL_BOX_HEIGHT = 60;
		public final static int ANTIAIRCRAFT_UNIT_COL_BOX_WIDTH = 60;
		
		//MELEE UNIT Stats
		
		public final static Type MELEE_UNIT_TYPE = Type.GROUND;
		public final static boolean MELEE_UNIT_ATTACK_FLYING = false;
		public final static int MELEE_UNIT_MAX_HP = 700;
		public final static float MELEE_UNIT_ATTACK_SPEED = 9.0f;
		public final static int MELEE_UNIT_ATTACK_RANGE = 25;
		public final static int MELEE_UNIT_MOVEMENT_SPEED = 4;
		public final static int MELEE_UNIT_DAMAGE = 170;
		public final static int MELEE_UNIT_BOUNTY = 300;
		public final static int MELEE_UNIT_COST = 300;
		public final static int MELEE_UNIT_EXP = 70;
		public final static int MELEE_UNIT_CREATION_TIME = 280;
		
		public final static int MELEE_UNIT_MAX_HP_UPGRADE_RATE = 1;
		public final static float MELEE_UNIT_ATTACK_SPEED_UPGRADE_RATE = 0.5f;
		public final static int MELEE_UNIT_ATTACK_RANGE_UPGRADE_RATE = 5;
		public final static int MELEE_UNIT_MOVEMENT_SPEED_UPGRADE_RATE = 0;
		public final static int MELEE_UNIT_DAMAGE_UPGRADE_RATE = 10;
		public final static int MELEE_UNIT_BOUNTY_UPGRADE_RATE = 10;
		public final static int MELEE_UNIT_COST_UPGRADE_RATE = 7;
		
		public final static int MELEE_UNIT_COL_BOX_HEIGHT = 90;
		public final static int MELEE_UNIT_COL_BOX_WIDTH = 90;
		
		//Tower Stats
		
		public final static float TOWER_ATTACK_SPEED = 7.0f;
		public final static int TOWER_ATTACK_RANGE = 100;
		public final static int TOWER_DAMAGE = 350;
		public final static int TOWER_COST = 500;
		
		public final static float TOWER_ATTACK_SPEED_BONUS = 9.0f;
		public final static int TOWER_ATTACK_RANGE_BONUS = 80;
		public final static int TOWER_DAMAGE_BONUS = 250;
		
		public final static int TOWER_HEIGHT = 200;
	
		///////////////////////////////////////////////////
		//UPGRADES
		///////////////////////////////////////////////////

		public final static int UNIT_UPGRADE_COST = 50;
		public final static int TOWER_DAMAGE_UPGRADE_COST = 300;
		public final static int TOWER_ATTACK_SPEED_UPGRADE_COST = 300;
		public final static int TOWER_ATTACK_RANGE_UPGRADE_COST = 300;
		
}	

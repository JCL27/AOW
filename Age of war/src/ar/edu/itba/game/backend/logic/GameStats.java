package ar.edu.itba.game.backend.logic;

public class GameStats {

	/**
	 * Base
	 */

	public final static int BASE_PLAYER_X = -10;
	public final static int BASE_AI_X = 954;
	public final static int BASE_WIDTH = 256;
	public final static int BASE_HEIGHT = 282;
	public final static int BASE_MAX_HP = 15000;
	
	/**
	 * Ranged Unit
	 */

	public final static Type RANGED_UNIT_TYPE = Type.GROUND;
	public final static boolean RANGED_UNIT_ATTACK_FLYING = false;
	public final static int RANGED_UNIT_MAX_HP = 300;
	public final static float RANGED_UNIT_ATTACK_SPEED = 15.0f;
	public final static int RANGED_UNIT_ATTACK_RANGE = 260;
	public final static int RANGED_UNIT_MOVEMENT_SPEED = 4;
	public final static int RANGED_UNIT_DAMAGE = 80;
	public final static int RANGED_UNIT_BOUNTY = 90;
	public final static int RANGED_UNIT_COST = 75;

	public final static int RANGED_UNIT_EXP = 70;
	public final static int RANGED_UNIT_CREATION_TIME = 70;

	public final static int RANGED_UNIT_MAX_HP_UPGRADE_RATE = 1;
	public final static double RANGED_UNIT_ATTACK_SPEED_UPGRADE_RATE = 0.5;
	public final static int RANGED_UNIT_ATTACK_RANGE_UPGRADE_RATE = 5;
	public final static int RANGED_UNIT_MOVEMENT_SPEED_UPGRADE_RATE = 0;
	public final static int RANGED_UNIT_DAMAGE_UPGRADE_RATE = 10;
	public final static int RANGED_UNIT_BOUNTY_UPGRADE_RATE = 10;
	public final static int RANGED_UNIT_COST_UPGRADE_RATE = 7;
	
	public final static int RANGED_UNIT_COL_BOX_HEIGHT = 50;
	public final static int RANGED_UNIT_COL_BOX_WIDTH = 50;

	/**
	 * Flying Unit
	 */
	
	public final static Type FLYING_UNIT_TYPE = Type.FLYING;
	public final static boolean FLYING_UNIT_ATTACK_FLYING = false;
	public final static int FLYING_UNIT_MAX_HP = 280;
	public final static float FLYING_UNIT_ATTACK_SPEED = 5.0f;
	public final static int FLYING_UNIT_ATTACK_RANGE = 220;
	public final static int FLYING_UNIT_MOVEMENT_SPEED = 4;
	public final static int FLYING_UNIT_DAMAGE = 130;
	public final static int FLYING_UNIT_BOUNTY = 300;
	public final static int FLYING_UNIT_COST = 180;
	public final static int FLYING_UNIT_EXP = 70;
	public final static int FLYING_UNIT_CREATION_TIME = 100;
	public final static int FLYING_UNIT_MAX_HP_UPGRADE_RATE = 1;
	public final static double FLYING_UNIT_ATTACK_SPEED_UPGRADE_RATE = 0.1;
	public final static int FLYING_UNIT_ATTACK_RANGE_UPGRADE_RATE = 0;
	public final static int FLYING_UNIT_MOVEMENT_SPEED_UPGRADE_RATE = 1;
	public final static int FLYING_UNIT_DAMAGE_UPGRADE_RATE = 1500;
	public final static int FLYING_UNIT_BOUNTY_UPGRADE_RATE = 10;
	public final static int FLYING_UNIT_COST_UPGRADE_RATE = 7;
	
	public final static int FLYING_UNIT_COL_BOX_HEIGHT = 150;
	public final static int FLYING_UNIT_COL_BOX_WIDTH = 100;

	/**
	 * Antiaircraft Unit
	 */

	public final static Type ANTIAIRCRAFT_UNIT_TYPE = Type.GROUND;
	public final static boolean ANTIAIRCRAFT_UNIT_ATTACK_FLYING = true;
	public final static int ANTIAIRCRAFT_UNIT_MAX_HP = 300;
	public final static float ANTIAIRCRAFT_UNIT_ATTACK_SPEED = 5.0f;
	public final static int ANTIAIRCRAFT_UNIT_ATTACK_RANGE = 300;
	public final static int ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED = 4;
	public final static int ANTIAIRCRAFT_UNIT_DAMAGE = 80;
	public final static int ANTIAIRCRAFT_UNIT_BOUNTY = 300;
	public final static int ANTIAIRCRAFT_UNIT_COST = 110;
	public final static int ANTIAIRCRAFT_UNIT_EXP = 70;
	public final static int ANTIAIRCRAFT_UNIT_CREATION_TIME = 150;
	public final static int ANTIAIRCRAFT_UNIT_MAX_HP_UPGRADE_RATE = 1;
	public final static double ANTIAIRCRAFT_UNIT_ATTACK_SPEED_UPGRADE_RATE = 0.1;
	public final static int ANTIAIRCRAFT_UNIT_ATTACK_RANGE_UPGRADE_RATE = 0;
	public final static int ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED_UPGRADE_RATE = 1;
	public final static int ANTIAIRCRAFT_UNIT_DAMAGE_UPGRADE_RATE = 10;
	public final static int ANTIAIRCRAFT_UNIT_BOUNTY_UPGRADE_RATE = 10;
	public final static int ANTIAIRCRAFT_UNIT_COST_UPGRADE_RATE = 7;

	
	public final static int ANTIAIRCRAFT_UNIT_COL_BOX_HEIGHT = 60;
	public final static int ANTIAIRCRAFT_UNIT_COL_BOX_WIDTH = 60;
	
	/**
	 * Melee Unit
	 */
	
	public final static Type MELEE_UNIT_TYPE = Type.GROUND;
	public final static boolean MELEE_UNIT_ATTACK_FLYING = false;
	public final static int MELEE_UNIT_MAX_HP = 900;
	public final static float MELEE_UNIT_ATTACK_SPEED = 9.0f;
	public final static int MELEE_UNIT_ATTACK_RANGE = 25;
	public final static int MELEE_UNIT_MOVEMENT_SPEED = 4;
	public final static int MELEE_UNIT_DAMAGE = 170;
	public final static int MELEE_UNIT_BOUNTY = 300;
	public final static int MELEE_UNIT_COST = 95;
	public final static int MELEE_UNIT_EXP = 70;
	public final static int MELEE_UNIT_CREATION_TIME = 280;
	public final static int MELEE_UNIT_MAX_HP_UPGRADE_RATE = 15000;
	public final static float MELEE_UNIT_ATTACK_SPEED_UPGRADE_RATE = 0.5f;
	public final static int MELEE_UNIT_ATTACK_RANGE_UPGRADE_RATE = 5;
	public final static int MELEE_UNIT_MOVEMENT_SPEED_UPGRADE_RATE = 0;
	public final static int MELEE_UNIT_DAMAGE_UPGRADE_RATE = 220;
	public final static int MELEE_UNIT_BOUNTY_UPGRADE_RATE = 10;
	public final static int MELEE_UNIT_COST_UPGRADE_RATE = 7;
	
	public final static int MELEE_UNIT_COL_BOX_HEIGHT = 90;
	public final static int MELEE_UNIT_COL_BOX_WIDTH = 90;
	
	/**
	 * Tower
	 */

	public final static float TOWER_ATTACK_SPEED = 7.0f;
	public final static int TOWER_ATTACK_RANGE = 100;
	public final static int TOWER_DAMAGE = 270;
	public final static int TOWER_COST = 500;
	
	public final static float TOWER_ATTACK_SPEED_BONUS = 9.0f;
	public final static int TOWER_ATTACK_RANGE_BONUS = 80;
	public final static int TOWER_DAMAGE_BONUS = 250;
	
	public final static int TOWER_HEIGHT = 200;
	
	public final static int TOWER_SCREEN_HEIGHT = 50;
	public final static int TOWER_SCREEN_WIDTH = 100;

	/**
	 * Upgrades
	 */

	public final static int UNIT_UPGRADE_COST = 50;
	public final static int TOWER_DAMAGE_UPGRADE_COST = 1000;
	public final static int TOWER_ATTACK_SPEED_UPGRADE_COST = 1000;
	public final static int TOWER_ATTACK_RANGE_UPGRADE_COST = 1000;
	public final static int ANTIAIRCRAFT_UNIT_RESEARCH_COST = 350;
	public final static int FLYING_UNIT_RESEARCH_COST = 450;
	
	public final static int INITIAL_GOLD = 700;
	public final static int INITIAL_EXP = 0;
	
	/**
	 * Labels
	 */
	public final static int BACKGROUND_LEFT_SIZE = 10;
	public final static int BACKGROUND_RIGHT_SIZE = 145;
	public final static int BACKGROUND_BOTTOM_SIZE = 10;
	public final static int BACKGROUND_TOP_SIZE = 10;
	public final static int HIDDEN_BACKGROUND_BOTTOM_SIZE = 120;

	public final static int LABEL_SEPARATION = 15;
	
	public final static int GOLD_LABEL_X = 15;
	public final static int GOLD_LABEL_Y = 750;
	public final static int EXP_LABEL_X = 15;
	public final static int EXP_LABEL_Y = 690;

	public final static int LABEL_UNITS_Y = 750;
	public final static int LABEL_FLYING_X = 400;
	public final static int LABEL_RANGED_X = 200;
	public final static int LABEL_MELEE_X = 800;
	public final static int LABEL_ANTIAIRCRAFT_X = 600;

}	

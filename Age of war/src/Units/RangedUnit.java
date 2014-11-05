package Units;

import java.io.Serializable;

import Observers.UnitObserver;
import ar.edu.itba.game.Element;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.WorldManager;

public class RangedUnit extends Unit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3399027554023024600L;
	private static Integer playerUnitLevel = 0;
	private static boolean playerAvailable = true;
	private static Integer AIUnitLevel = 0;
	private static boolean AIAvailable = true;
	
	public RangedUnit(Player player, UnitObserver observer){
		super(player, observer);
		this.bounty = GameStats.RANGED_UNIT_BOUNTY;
		this.cost = GameStats.RANGED_UNIT_COST;
		this.exp = GameStats.RANGED_UNIT_EXP;
		
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
			this.maxHp = (int) (GameStats.RANGED_UNIT_MAX_HP + Math.sqrt(playerUnitLevel * GameStats.RANGED_UNIT_MAX_HP_UPGRADE_RATE));
			this.hp = this.maxHp;
			this.attackSpeed = GameStats.RANGED_UNIT_ATTACK_SPEED + Math.sqrt(playerUnitLevel * GameStats.RANGED_UNIT_ATTACK_SPEED_UPGRADE_RATE);
			this.attackRange = (int) (GameStats.RANGED_UNIT_ATTACK_RANGE + Math.sqrt(playerUnitLevel * GameStats.RANGED_UNIT_ATTACK_RANGE_UPGRADE_RATE));
			this.damage = (int) (GameStats.RANGED_UNIT_DAMAGE + Math.sqrt(playerUnitLevel * GameStats.RANGED_UNIT_DAMAGE_UPGRADE_RATE));
			this.element = new Element(100, Game.GROUND_HEIGHT, 3, 0, GameStats.RANGED_UNIT_COL_BOX_HEIGHT, GameStats.RANGED_UNIT_COL_BOX_WIDTH, false);
			this.movementSpeed = (int) (GameStats.RANGED_UNIT_MOVEMENT_SPEED + Math.sqrt(playerUnitLevel * GameStats.RANGED_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
			this.dir = Side.LEFT;
		}else{
			this.maxHp = (int) (GameStats.RANGED_UNIT_MAX_HP + Math.sqrt(AIUnitLevel * GameStats.RANGED_UNIT_MAX_HP_UPGRADE_RATE));
			this.hp = this.maxHp;
			this.attackSpeed = GameStats.RANGED_UNIT_ATTACK_SPEED + Math.sqrt(AIUnitLevel * GameStats.RANGED_UNIT_ATTACK_SPEED_UPGRADE_RATE);
			this.attackRange = (int) (GameStats.RANGED_UNIT_ATTACK_RANGE + Math.sqrt(AIUnitLevel * GameStats.RANGED_UNIT_ATTACK_RANGE_UPGRADE_RATE));
			this.damage = (int) (GameStats.RANGED_UNIT_DAMAGE + Math.sqrt(AIUnitLevel * GameStats.RANGED_UNIT_DAMAGE_UPGRADE_RATE));
			this.element = new Element(1000, Game.GROUND_HEIGHT, 3, 0, GameStats.RANGED_UNIT_COL_BOX_HEIGHT, GameStats.RANGED_UNIT_COL_BOX_WIDTH, false);
			this.movementSpeed = (-1) * (int) (GameStats.RANGED_UNIT_MOVEMENT_SPEED + Math.sqrt(AIUnitLevel * GameStats.RANGED_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
			this.dir = Side.RIGHT;
		}
		this.type = GameStats.RANGED_UNIT_TYPE;
		this.attackFlying = false;
		WorldManager.getInstance().getElements().add(this.element);
		
	}
	public static void playerLevelUp() {
		playerUnitLevel++;
		
	}

	public static void AILevelUp() {
		AIUnitLevel++;
		
	}
	
	public static String[] getLevels(){
		String[] str = {playerUnitLevel.toString(), AIUnitLevel.toString()};
		return str;
	}
	
	public static void setLevels(String[] row){
		playerUnitLevel = Integer.parseInt(row[0]);
		AIUnitLevel = Integer.parseInt(row[1]);
	}
	
	public static int getCost(Player player){
		if(player == WorldManager.getInstance().getPlayerAI())
			return (int) (GameStats.RANGED_UNIT_COST + Math.sqrt(AIUnitLevel * GameStats.RANGED_UNIT_COST_UPGRADE_RATE));
		return (int) (GameStats.RANGED_UNIT_COST + Math.sqrt(playerUnitLevel * GameStats.RANGED_UNIT_COST_UPGRADE_RATE));
	}
	
	public static boolean isPlayerAvailable() {
		return playerAvailable;
	}

	public static void setPlayerAvailable(boolean playerAvailable) {
		RangedUnit.playerAvailable = playerAvailable;
	}

	public static boolean isAIAvailable() {
		return AIAvailable;
	}

	public static void setAIAvailable(boolean aIAvailable) {
		AIAvailable = aIAvailable;
	}
	
	public static int getCreationTime() {
		return GameStats.RANGED_UNIT_CREATION_TIME;
	}
}

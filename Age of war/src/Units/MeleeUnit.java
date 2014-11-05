package Units;

import Observers.UnitObserver;
import ar.edu.itba.game.Attackable;
import ar.edu.itba.game.Element;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.WorldManager;
import exceptions.DeadUnitException;
 //TODO: ADD UPGRADES
public class MeleeUnit extends Unit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6458482342043685784L;
	private static Integer playerUnitLevel = 0;
	private static boolean playerAvailable = true;
	private static Integer AIUnitLevel = 0;
	private static boolean AIAvailable = true;
	
	public MeleeUnit(Player player, UnitObserver observer){
		super(player, observer);
		this.bounty = GameStats.MELEE_UNIT_BOUNTY;
		this.cost = GameStats.MELEE_UNIT_COST;
		this.exp = GameStats.MELEE_UNIT_EXP;
		
			if (this.player.equals(WorldManager.getInstance().getPlayer())){
				this.maxHp = (int) (GameStats.MELEE_UNIT_MAX_HP + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_MAX_HP_UPGRADE_RATE));
				this.hp = this.maxHp;
				this.attackSpeed = GameStats.MELEE_UNIT_ATTACK_SPEED + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_ATTACK_SPEED_UPGRADE_RATE);
				this.attackRange = (int) (GameStats.MELEE_UNIT_ATTACK_RANGE + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_ATTACK_RANGE_UPGRADE_RATE));
				this.damage = (int) (GameStats.MELEE_UNIT_DAMAGE + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_DAMAGE_UPGRADE_RATE));
				this.element = new Element(100, Game.GROUND_HEIGHT, 3, 0, GameStats.MELEE_UNIT_COL_BOX_HEIGHT, GameStats.MELEE_UNIT_COL_BOX_WIDTH, false);
				this.movementSpeed = (int) (GameStats.MELEE_UNIT_MOVEMENT_SPEED + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
				this.dir = Side.LEFT;
			}else{
				this.maxHp = (int) (GameStats.MELEE_UNIT_MAX_HP + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_MAX_HP_UPGRADE_RATE));
				this.hp = this.maxHp;
				this.attackSpeed = GameStats.MELEE_UNIT_ATTACK_SPEED + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_ATTACK_SPEED_UPGRADE_RATE);
				this.attackRange = (int) (GameStats.MELEE_UNIT_ATTACK_RANGE + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_ATTACK_RANGE_UPGRADE_RATE));
				this.damage = (int) (GameStats.MELEE_UNIT_DAMAGE + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_DAMAGE_UPGRADE_RATE));
				this.element = new Element(1000, Game.GROUND_HEIGHT, 3, 0, GameStats.MELEE_UNIT_COL_BOX_HEIGHT, GameStats.MELEE_UNIT_COL_BOX_WIDTH, false);
				this.movementSpeed = (-1) * (int) (GameStats.MELEE_UNIT_MOVEMENT_SPEED + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
				this.dir = Side.RIGHT;
			}
			
			this.type = GameStats.MELEE_UNIT_TYPE;
			this.attackFlying = false;
			WorldManager.getInstance().getElements().add(this.element);
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
			return (int) (GameStats.MELEE_UNIT_COST + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_COST_UPGRADE_RATE));
		return (int) (GameStats.MELEE_UNIT_COST + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_COST_UPGRADE_RATE));
	}
	
	public static boolean isPlayerAvailable() {
		return playerAvailable;

	}
	
	public static void setPlayerAvailable(boolean playerAvailable) {
		MeleeUnit.playerAvailable = playerAvailable;
	}

	public static boolean isAIAvailable() {
		return AIAvailable;
	}

	public static void setAIAvailable(boolean aIAvailable) {
		AIAvailable = aIAvailable;
	}	
	public void attack(Attackable objective){
		if(this.cooldown == 0){
			try {
				this.objective.receiveDamage(this.damage);
			} catch (DeadUnitException e) {
				WorldManager.getInstance().killUnit((Unit)this.objective);
			}
			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;
		}
	}
	
	public static void playerLevelUp() {
		playerUnitLevel++;
	}

	public static void AILevelUp() {
		AIUnitLevel++;
	}
	public static int getCreationTime() {
		return GameStats.MELEE_UNIT_CREATION_TIME;
	}
}

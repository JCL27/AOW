package Units;

import ar.edu.itba.game.Element;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.WorldManager;
import Observers.UnitObserver;

public class RangedUnit extends Unit {
	private static int playerUnitLevel = 0;
	private static int AIUnitLevel = 0;
	
	public RangedUnit(Player player){
		
		this.player = player;
		this.objective = null;
		this.cooldown = 0;
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
		this.observer = new UnitObserver(this);
		this.addObserver(this.observer);
		this.attackFlying = false;
		WorldManager.getInstance().getElements().add(this.element);
		
	}
	public static void playerLevelUp() {
		playerUnitLevel++;
		
	}

	public static void AILevelUp() {
		AIUnitLevel++;
		
	}
	
}

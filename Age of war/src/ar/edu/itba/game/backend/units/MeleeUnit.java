package ar.edu.itba.game.backend.units;

import ar.edu.itba.game.backend.logic.Attackable;
import ar.edu.itba.game.backend.logic.Element;
import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.frontend.observers.UnitObserver;

public class MeleeUnit extends Unit {
	private static final long serialVersionUID = 6458482342043685784L;
	
	/**
	 * States whether the unit is available for purchase
	 */
	
	public MeleeUnit(Player player, UnitObserver observer){
		super(player, observer);
		this.bounty = GameStats.MELEE_UNIT_BOUNTY;
		this.cost = GameStats.MELEE_UNIT_COST;
		this.exp = GameStats.MELEE_UNIT_EXP;
		
		this.UnitLevel = UnitsLevels.getInstance().getLevel(this.player, UnitType.ANTIAIRCRAFT_UNIT);
		
		this.maxHp = (int) (GameStats.MELEE_UNIT_MAX_HP + Math.sqrt(UnitLevel * GameStats.MELEE_UNIT_MAX_HP_UPGRADE_RATE));
		this.hp = this.maxHp;
		this.attackSpeed = GameStats.MELEE_UNIT_ATTACK_SPEED + Math.sqrt(UnitLevel * GameStats.MELEE_UNIT_ATTACK_SPEED_UPGRADE_RATE);
		this.attackRange = (int) (GameStats.MELEE_UNIT_ATTACK_RANGE + Math.sqrt(UnitLevel * GameStats.MELEE_UNIT_ATTACK_RANGE_UPGRADE_RATE));
		this.movementSpeed = (int) (GameStats.MELEE_UNIT_MOVEMENT_SPEED + Math.sqrt(UnitLevel * GameStats.MELEE_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
		
			if (this.player.equals(WorldManager.getInstance().getPlayer())){
				this.damage = (int) (GameStats.MELEE_UNIT_DAMAGE + Math.sqrt(UnitLevel * GameStats.MELEE_UNIT_DAMAGE_UPGRADE_RATE));
				this.element = new Element(100, Game.GROUND_HEIGHT, 3, 0, GameStats.MELEE_UNIT_COL_BOX_HEIGHT, GameStats.MELEE_UNIT_COL_BOX_WIDTH, false);
				this.dir = Side.LEFT;
			}else{
				this.element = new Element(1000, Game.GROUND_HEIGHT, 3, 0, GameStats.MELEE_UNIT_COL_BOX_HEIGHT, GameStats.MELEE_UNIT_COL_BOX_WIDTH, false);
				this.movementSpeed *= (-1);
				this.dir = Side.RIGHT;
			}
			this.type = GameStats.MELEE_UNIT_TYPE;
			this.attackFlying = false;
			WorldManager.getInstance().getElements().add(this.element);
		}
	
	/**
	 * Attacks an attackable objective at short range
	 */
	public void attack(Attackable objective){
		if(this.cooldown == 0){
			this.objective.receiveDamage(this.damage);

			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;
		}
	}
	

	public static int getCreationTime() {
		return GameStats.MELEE_UNIT_CREATION_TIME;
	}
	
}

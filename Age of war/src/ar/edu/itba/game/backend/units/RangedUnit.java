package ar.edu.itba.game.backend.units;

import java.io.Serializable;

import ar.edu.itba.game.backend.logic.Attackable;
import ar.edu.itba.game.backend.logic.Element;
import ar.edu.itba.game.backend.logic.Factory;
import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.frontend.observers.UnitObserver;

public class RangedUnit extends Unit implements Serializable{
	
	private static final long serialVersionUID = -3399027554023024600L;
	
	/**
	 * States whether the unit is available for purchase
	 */
	
	public RangedUnit(Player player, UnitObserver observer){
		super(player, observer);
		this.bounty = GameStats.RANGED_UNIT_BOUNTY;
		this.cost = GameStats.RANGED_UNIT_COST;
		this.exp = GameStats.RANGED_UNIT_EXP;
		
		this.UnitLevel = UnitsLevels.getInstance().getLevel(this.player, UnitType.ANTIAIRCRAFT_UNIT);
		
		this.maxHp = (int) (GameStats.RANGED_UNIT_MAX_HP + Math.sqrt(UnitLevel * GameStats.RANGED_UNIT_MAX_HP_UPGRADE_RATE));
		this.hp = this.maxHp;
		this.attackSpeed = GameStats.RANGED_UNIT_ATTACK_SPEED + Math.sqrt(UnitLevel * GameStats.RANGED_UNIT_ATTACK_SPEED_UPGRADE_RATE);
		this.attackRange = (int) (GameStats.RANGED_UNIT_ATTACK_RANGE + Math.sqrt(UnitLevel * GameStats.RANGED_UNIT_ATTACK_RANGE_UPGRADE_RATE));
		this.damage = (int) (GameStats.RANGED_UNIT_DAMAGE + Math.sqrt(UnitLevel * GameStats.RANGED_UNIT_DAMAGE_UPGRADE_RATE));
		this.movementSpeed = (int) (GameStats.RANGED_UNIT_MOVEMENT_SPEED + Math.sqrt(UnitLevel * GameStats.RANGED_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));

		
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
			this.element = new Element(100, Game.GROUND_HEIGHT, 3, 0, GameStats.RANGED_UNIT_COL_BOX_HEIGHT, GameStats.RANGED_UNIT_COL_BOX_WIDTH, false);
			this.dir = Side.LEFT;
		}else{
			this.element = new Element(1000, Game.GROUND_HEIGHT, 3, 0, GameStats.RANGED_UNIT_COL_BOX_HEIGHT, GameStats.RANGED_UNIT_COL_BOX_WIDTH, false);
			this.movementSpeed *= (-1);
			this.dir = Side.RIGHT;
		}
		this.type = GameStats.RANGED_UNIT_TYPE;
		this.attackFlying = false;
		WorldManager.getInstance().getElements().add(this.element);
		
	}
	
	/**
	 * Fires a projectile towards an attackable objective.
	 * (Only targets ground units)
	 */
	public void attack(Attackable objective){
		if(this.cooldown == 0){
			float velX;
			double velY = Math.sqrt(Math.abs(this.getElement().getMiddleX() - objective.getElement().getMiddleX()) *
					Game.GRAVITY / 2);
			if(this.getSide()==Side.RIGHT)
				velX = (float)-velY;
			else
				velX = (float)velY;
			this.player.getProjectiles().add(Factory.getInstance().createProjectile(this.getElement().getMiddleX(),
					this.getElement().getMiddleY(), velX , (float)velY , true, this.damage));
			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;
		}
	}
	
	public static int getCreationTime() {
		return GameStats.RANGED_UNIT_CREATION_TIME;
	}
}

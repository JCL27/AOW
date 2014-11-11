package ar.edu.itba.game.backend.units;

import ar.edu.itba.game.backend.exceptions.UnavailableUnitException;
import ar.edu.itba.game.backend.logic.Attackable;
import ar.edu.itba.game.backend.logic.Element;
import ar.edu.itba.game.backend.logic.Factory;
import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.frontend.observers.UnitObserver;

public class FlyingUnit extends Unit{
	
	private static final long serialVersionUID = -7357772180229981544L;
	
	/**
	 * States whether the unit is available for purchase
	 */
	
	public FlyingUnit(Player player, UnitObserver observer) throws UnavailableUnitException{
		super(player, observer);
		checkIfAvailable(player);
		this.bounty = GameStats.FLYING_UNIT_BOUNTY;
		this.cost = GameStats.FLYING_UNIT_COST;
		this.exp = GameStats.FLYING_UNIT_EXP;
		
		this.UnitLevel = UnitsLevels.getInstance().getLevel(this.player, UnitType.ANTIAIRCRAFT_UNIT);
		
		this.maxHp = (int) (GameStats.FLYING_UNIT_MAX_HP + Math.sqrt(UnitLevel * GameStats.FLYING_UNIT_MAX_HP_UPGRADE_RATE));
		this.hp = this.maxHp;
		this.attackSpeed = GameStats.FLYING_UNIT_ATTACK_SPEED + Math.sqrt(UnitLevel * GameStats.FLYING_UNIT_ATTACK_SPEED_UPGRADE_RATE);
		this.attackRange = (int) (GameStats.FLYING_UNIT_ATTACK_RANGE + Math.sqrt(UnitLevel * GameStats.FLYING_UNIT_ATTACK_RANGE_UPGRADE_RATE));
		this.damage = (int) (GameStats.FLYING_UNIT_DAMAGE + Math.sqrt(UnitLevel * GameStats.FLYING_UNIT_DAMAGE_UPGRADE_RATE));
		this.movementSpeed = (int) (GameStats.FLYING_UNIT_MOVEMENT_SPEED + Math.sqrt(UnitLevel * GameStats.FLYING_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));

		
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
			this.element = new Element(100, Game.GROUND_HEIGHT + Game.FLYING_HEIGHT, 3, 0, GameStats.FLYING_UNIT_COL_BOX_HEIGHT, GameStats.FLYING_UNIT_COL_BOX_WIDTH, false);
			this.dir = Side.LEFT;
		}else{
			this.element = new Element(1000, Game.GROUND_HEIGHT + Game.FLYING_HEIGHT, 3, 0, GameStats.FLYING_UNIT_COL_BOX_HEIGHT, GameStats.FLYING_UNIT_COL_BOX_WIDTH, false);
			this.movementSpeed *= (-1);
			this.dir = Side.RIGHT;
		}
		
		this.type = GameStats.FLYING_UNIT_TYPE;
		this.attackFlying = false;
		WorldManager.getInstance().getElements().add(this.element);
	}
	
	public void checkIfAvailable(Player player) throws UnavailableUnitException{
		if(player == WorldManager.getInstance().getPlayer()){
			if(!UnitsLevels.getInstance().isPlayerFlyingUnitAvailable())
				throw new UnavailableUnitException();
		}else if (!UnitsLevels.getInstance().isAIFlyingUnitAvailable()){
			throw new UnavailableUnitException();
		}
	}
	
	/**
	 * Fires a projectile towards an attackable objective.
	 * (Only targets ground units)
	 */
	public void attack(Attackable objective){
		if(this.cooldown == 0){
			float velX;
			float velY = 0;
			float Yf = objective.getElement().getMiddleY();
			float Xf = objective.getElement().getMiddleX();
			float X = this.getElement().getMiddleX();
			float Y = this.getElement().getY()-30;

			velX = (float) Math.sqrt(-Game.GRAVITY * (Xf - X) * (Xf - X) / ((Yf - Y)*2)); 
			if(this.getSide()==Side.RIGHT)
				velX = -velX;
			this.player.getProjectiles().add(Factory.getInstance().createProjectile(this.getElement().getMiddleX(),
					this.getElement().getY()-30, velX , (float)velY , true, this.damage));
			
			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;
		}
	}
	
	public static int getCreationTime() {
		return GameStats.FLYING_UNIT_CREATION_TIME;
	}
}

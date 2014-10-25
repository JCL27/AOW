package ar.edu.itba.game;

import Observers.UnitObserver;

public class FlyingUnit extends Unit{
	public FlyingUnit(Player player){
		this.player = player;
		this.objective = null;
		this.cooldown = 0;
		this.bounty = GameStats.FLYING_UNIT_BOUNTY;
		this.cost = GameStats.FLYING_UNIT_COST;
		this.exp = GameStats.FLYING_UNIT_EXP;
		
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
			this.maxHp = (int) (GameStats.FLYING_UNIT_MAX_HP + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_MAX_HP_UPGRADE_RATE));
			this.hp = this.maxHp;
			this.attackSpeed = GameStats.FLYING_UNIT_ATTACK_SPEED + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_ATTACK_SPEED_UPGRADE_RATE);
			this.attackRange = (int) (GameStats.FLYING_UNIT_ATTACK_RANGE + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_ATTACK_RANGE_UPGRADE_RATE));
			this.damage = (int) (GameStats.FLYING_UNIT_DAMAGE + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_DAMAGE_UPGRADE_RATE));
			this.element = new Element(100, Game.GROUND_HEIGHT + Game.FLYING_HEIGHT, 3, 0, 100, 100, false);
			this.movementSpeed = (int) (GameStats.FLYING_UNIT_MOVEMENT_SPEED + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
			this.dir = Side.LEFT;
		}else{
			this.maxHp = (int) (GameStats.FLYING_UNIT_MAX_HP + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_MAX_HP_UPGRADE_RATE));
			this.hp = this.maxHp;
			this.attackSpeed = GameStats.FLYING_UNIT_ATTACK_SPEED + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_ATTACK_SPEED_UPGRADE_RATE);
			this.attackRange = (int) (GameStats.FLYING_UNIT_ATTACK_RANGE + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_ATTACK_RANGE_UPGRADE_RATE));
			this.damage = (int) (GameStats.FLYING_UNIT_DAMAGE + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_DAMAGE_UPGRADE_RATE));
			this.element = new Element(1000, Game.GROUND_HEIGHT + Game.FLYING_HEIGHT, 3, 0, 100, 100, false);
			this.movementSpeed = (-1) * (int) (GameStats.FLYING_UNIT_MOVEMENT_SPEED + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
			this.dir = Side.RIGHT;
		}
		
		this.type = GameStats.FLYING_UNIT_TYPE;
		this.observer = new UnitObserver(this);
		this.addObserver(this.observer);
		this.attackFlying = false;
		WorldManager.getInstance().getElements().add(this.element);
	}
	
	public void attack(Attackable objective){
		if(this.cooldown == 0){
			float velX;
			float velY = 0;
			float Yf = objective.getElement().getMiddleY();
			float Xf = objective.getElement().getMiddleX();
			float X = this.getElement().getMiddleX();
			float Y = this.getElement().getY()-30;

			velX = (float) Math.sqrt(-Game.GRAVITY * (Xf - X) * (Xf - X) / ((Yf - Y)*2)); 
			System.out.println("velocidad " + velX);			
			if(this.getSide()==Side.RIGHT)
				velX = -velX;
			this.player.getProjectiles().add(new Projectile(this.getElement().getMiddleX(),
					this.getElement().getY()-30, velX , (float)velY , true, this.damage));
			
			//System.out.println("attack!");
			System.out.println("velocidad " + velX);
			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;
		}
	}
	
}

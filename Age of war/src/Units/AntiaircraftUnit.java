package Units;

import Observers.UnitObserver;
import ar.edu.itba.game.Attackable;
import ar.edu.itba.game.Element;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Projectile;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.WorldManager;

public class AntiaircraftUnit extends Unit{
	
	public AntiaircraftUnit(Player player){
		this.player = player;
		this.objective = null;
		this.cooldown = 0;
		this.bounty = GameStats.ANTIAIRCRAFT_UNIT_BOUNTY;
		this.cost = GameStats.ANTIAIRCRAFT_UNIT_COST;
		this.exp = GameStats.ANTIAIRCRAFT_UNIT_EXP;
		
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
			this.maxHp = (int) (GameStats.ANTIAIRCRAFT_UNIT_MAX_HP + Math.sqrt(playerUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_MAX_HP_UPGRADE_RATE));
			this.hp = this.maxHp;
			this.attackSpeed = GameStats.ANTIAIRCRAFT_UNIT_ATTACK_SPEED + Math.sqrt(playerUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_ATTACK_SPEED_UPGRADE_RATE);
			this.attackRange = (int) (GameStats.ANTIAIRCRAFT_UNIT_ATTACK_RANGE + Math.sqrt(playerUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_ATTACK_RANGE_UPGRADE_RATE));
			this.damage = (int) (GameStats.ANTIAIRCRAFT_UNIT_DAMAGE + Math.sqrt(playerUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_DAMAGE_UPGRADE_RATE));
			this.element = new Element(100, Game.GROUND_HEIGHT, 3, 0, GameStats.ANTIAIRCRAFT_UNIT_COL_BOX_HEIGHT, GameStats.ANTIAIRCRAFT_UNIT_COL_BOX_WIDTH, false);
			this.movementSpeed = (int) (GameStats.ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED + Math.sqrt(playerUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
			this.dir = Side.LEFT;
		}else{
			this.maxHp = (int) (GameStats.ANTIAIRCRAFT_UNIT_MAX_HP + Math.sqrt(AIUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_MAX_HP_UPGRADE_RATE));
			this.hp = this.maxHp;
			this.attackSpeed = GameStats.ANTIAIRCRAFT_UNIT_ATTACK_SPEED + Math.sqrt(AIUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_ATTACK_SPEED_UPGRADE_RATE);
			this.attackRange = (int) (GameStats.ANTIAIRCRAFT_UNIT_ATTACK_RANGE + Math.sqrt(AIUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_ATTACK_RANGE_UPGRADE_RATE));
			this.damage = (int) (GameStats.ANTIAIRCRAFT_UNIT_DAMAGE + Math.sqrt(AIUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_DAMAGE_UPGRADE_RATE));
			this.element = new Element(1000, Game.GROUND_HEIGHT, 3, 0, GameStats.ANTIAIRCRAFT_UNIT_COL_BOX_HEIGHT, GameStats.ANTIAIRCRAFT_UNIT_COL_BOX_WIDTH, false);
			this.movementSpeed = (-1) * (int) (GameStats.ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED + Math.sqrt(AIUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
			this.dir = Side.RIGHT;
		}
		this.type = GameStats.ANTIAIRCRAFT_UNIT_TYPE;
		this.observer = new UnitObserver(this);
		this.addObserver(this.observer);
		this.attackFlying = GameStats.ANTIAIRCRAFT_UNIT_ATTACK_FLYING;
		WorldManager.getInstance().getElements().add(this.element);
	}
	
	public void attack(Attackable objective){
		if(this.cooldown == 0){
			float velX = 0;
			float velY = 0;
			if(objective.doesFly()){
				float Yf = objective.getElement().getMiddleY();
				float Xf = objective.getElement().getMiddleX();
				float X = this.getElement().getMiddleX();
				float Y = this.getElement().getMiddleY();
				float t = 0;
				float dist = Xf - X;
				float altura = Yf - Y;
				velY = (float) Math.sqrt(2 * Game.GRAVITY * (Yf - Y));
				t = (float) ((-velY + Math.sqrt(velY*velY-4*altura*Game.GRAVITY/2))/Game.GRAVITY);
				velX = - dist / t;
				
				//float vel = (float) Math.sqrt((velX*velX+velY*velY));
				
				
				
			/*	if(this.getSide()==Side.RIGHT)
					velX = (float)-velY;
				else
					velX = (float)velY;*/
			}else{

				velY = (float) Math.sqrt(Math.abs(this.getElement().getMiddleX() - objective.getElement().getMiddleX()) *
						Game.GRAVITY / 2);
				if(this.getSide()==Side.RIGHT)
					velX = (float)-velY;
				else
					velX = (float)velY;
			}
			this.player.getProjectiles().add(new Projectile(this.getElement().getMiddleX(),
					this.getElement().getMiddleY(), velX , (float)velY , true, this.damage));
			
			//System.out.println("attack!");

			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;
		}
	}
	
}

package ar.edu.itba.game.backend.units;

import ar.edu.itba.game.backend.logic.Attackable;
import ar.edu.itba.game.backend.logic.Element;
import ar.edu.itba.game.backend.logic.Factory;
import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.frontend.observers.UnitObserver;

public class AntiaircraftUnit extends Unit{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8389088340073951657L;
	private static Integer playerUnitLevel = 0;
	private static Boolean playerAvailable = false;
	private static Integer AIUnitLevel = 0;
	private static Boolean AIAvailable = false;
	
	public static boolean isPlayerAvailable() {
		return playerAvailable;
	}

	public static void setPlayerAvailable(boolean playerAvailable) {
		AntiaircraftUnit.playerAvailable = playerAvailable;
	}

	public static boolean isAIAvailable() {
		return AIAvailable;
	}

	public static void setAIAvailable(boolean aIAvailable) {
		AIAvailable = aIAvailable;
	}

	public AntiaircraftUnit(Player player, UnitObserver observer){
		super(player, observer);
		
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
				float height = Yf - Y;
				velY = (float) Math.sqrt(2 * Game.GRAVITY * (Yf - Y));
				t = (float) ((-velY + Math.sqrt(Math.abs(velY*velY-4*height*Game.GRAVITY/2)))/Game.GRAVITY);
				velX = - dist / t;
				
			}else{

				velY = (float) Math.sqrt(Math.abs(this.getElement().getMiddleX() - objective.getElement().getMiddleX()) *
						Game.GRAVITY / 2);
				if(this.getSide()==Side.RIGHT)
					velX = (float)-velY;
				else
					velX = (float)velY;
			}
			this.player.getProjectiles().add(Factory.getInstance().createProjectile(this.getElement().getMiddleX(),
					this.getElement().getMiddleY(), velX , (float)velY , true, this.damage));
			
			//System.out.println("attack!");

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
	
	public static int getCost(Player player){
		if(player == WorldManager.getInstance().getPlayerAI())
			return (int) (GameStats.ANTIAIRCRAFT_UNIT_COST + Math.sqrt(AIUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_COST_UPGRADE_RATE));
		return (int) (GameStats.ANTIAIRCRAFT_UNIT_COST + Math.sqrt(playerUnitLevel * GameStats.ANTIAIRCRAFT_UNIT_COST_UPGRADE_RATE));
	}
	
	public static String[] getLevels(){
		String[] str = {playerUnitLevel.toString(), AIUnitLevel.toString()};
		return str;
	}
	
	public static String[] getResearch(){
		String[] str = {playerAvailable.toString(), AIAvailable.toString()};
		return str;
	}
	
	public static void setLevels(String[] row){
		playerUnitLevel = Integer.parseInt(row[0]);
		AIUnitLevel = Integer.parseInt(row[1]);
	}
	
	public static void setResearch(String[] row){
		playerAvailable = Boolean.parseBoolean(row[0]);
		AIAvailable = Boolean.parseBoolean(row[1]);
	}
	
	public static Integer getPlayerUnitLevel(){ 
		return playerUnitLevel;
	}
	
	public static int getCreationTime() {
		return GameStats.ANTIAIRCRAFT_UNIT_CREATION_TIME;
	}
}

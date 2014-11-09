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
	private static Integer playerUnitLevel = 0;
	private static Integer AIUnitLevel = 0;
	
	/**
	 * States whether the unit is available for purchase
	 */
	private static Boolean playerAvailable = true;
	private static Boolean AIAvailable = true;
	
	public FlyingUnit(Player player, UnitObserver observer) throws UnavailableUnitException{
		super(player, observer);
		checkIfAvailable(player);
		this.bounty = GameStats.FLYING_UNIT_BOUNTY;
		this.cost = GameStats.FLYING_UNIT_COST;
		this.exp = GameStats.FLYING_UNIT_EXP;
		
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
			this.maxHp = (int) (GameStats.FLYING_UNIT_MAX_HP + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_MAX_HP_UPGRADE_RATE));
			this.hp = this.maxHp;
			this.attackSpeed = GameStats.FLYING_UNIT_ATTACK_SPEED + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_ATTACK_SPEED_UPGRADE_RATE);
			this.attackRange = (int) (GameStats.FLYING_UNIT_ATTACK_RANGE + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_ATTACK_RANGE_UPGRADE_RATE));
			this.damage = (int) (GameStats.FLYING_UNIT_DAMAGE + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_DAMAGE_UPGRADE_RATE));
			this.element = new Element(100, Game.GROUND_HEIGHT + Game.FLYING_HEIGHT, 3, 0, GameStats.FLYING_UNIT_COL_BOX_HEIGHT, GameStats.FLYING_UNIT_COL_BOX_WIDTH, false);
			this.movementSpeed = (int) (GameStats.FLYING_UNIT_MOVEMENT_SPEED + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
			this.dir = Side.LEFT;
		}else{
			this.maxHp = (int) (GameStats.FLYING_UNIT_MAX_HP + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_MAX_HP_UPGRADE_RATE));
			this.hp = this.maxHp;
			this.attackSpeed = GameStats.FLYING_UNIT_ATTACK_SPEED + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_ATTACK_SPEED_UPGRADE_RATE);
			this.attackRange = (int) (GameStats.FLYING_UNIT_ATTACK_RANGE + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_ATTACK_RANGE_UPGRADE_RATE));
			this.damage = (int) (GameStats.FLYING_UNIT_DAMAGE + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_DAMAGE_UPGRADE_RATE));
			this.element = new Element(1000, Game.GROUND_HEIGHT + Game.FLYING_HEIGHT, 3, 0, GameStats.FLYING_UNIT_COL_BOX_HEIGHT, GameStats.FLYING_UNIT_COL_BOX_WIDTH, false);
			this.movementSpeed = (-1) * (int) (GameStats.FLYING_UNIT_MOVEMENT_SPEED + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
			this.dir = Side.RIGHT;
		}
		
		this.type = GameStats.FLYING_UNIT_TYPE;
		this.attackFlying = false;
		WorldManager.getInstance().getElements().add(this.element);
	}
	
	public void checkIfAvailable(Player player) throws UnavailableUnitException{
		if(player == WorldManager.getInstance().getPlayer()){
			if(!playerAvailable)
				throw new UnavailableUnitException();
		}else if (!AIAvailable){
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
	
	public static int getCost(Player player){
		if(player == WorldManager.getInstance().getPlayerAI())
			return (int) (GameStats.FLYING_UNIT_COST + Math.sqrt(AIUnitLevel * GameStats.FLYING_UNIT_COST_UPGRADE_RATE));
		return (int) (GameStats.FLYING_UNIT_COST + Math.sqrt(playerUnitLevel * GameStats.FLYING_UNIT_COST_UPGRADE_RATE));
	}
	
	public static boolean isPlayerAvailable() {
		return playerAvailable;
	}

	public static void setPlayerAvailable(boolean playerAvailable) {
		FlyingUnit.playerAvailable = playerAvailable;
	}

	public static boolean isAIAvailable() {
		return AIAvailable;
	}

	public static void setAIAvailable(boolean aIAvailable) {
		AIAvailable = aIAvailable;
	}
	public static int getCreationTime() {
		return GameStats.FLYING_UNIT_CREATION_TIME;
	}
}

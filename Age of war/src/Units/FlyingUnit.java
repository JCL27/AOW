package Units;

import Observers.UnitObserver;
import ar.edu.itba.game.Attackable;
import ar.edu.itba.game.Element;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.UnitFactory;
import ar.edu.itba.game.WorldManager;
import exceptions.UnavailableUnitException;

public class FlyingUnit extends Unit{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7357772180229981544L;
	private static Integer playerUnitLevel = 0;
	private static boolean playerAvailable = false;
	private static Integer AIUnitLevel = 0;
	private static boolean AIAvailable = false;
	
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
	
	public void attack(Attackable objective){
		if(this.cooldown == 0){
			float velX;
			float velY = 0;
			float Yf = objective.getElement().getMiddleY();
			float Xf = objective.getElement().getMiddleX();
			float X = this.getElement().getMiddleX();
			float Y = this.getElement().getY()-30;

			velX = (float) Math.sqrt(-Game.GRAVITY * (Xf - X) * (Xf - X) / ((Yf - Y)*2)); 
			//System.out.println("velocidad " + velX);			
			if(this.getSide()==Side.RIGHT)
				velX = -velX;
			this.player.getProjectiles().add(UnitFactory.getInstance().createProjectile(this.getElement().getMiddleX(),
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
	
	public static void setLevels(String[] row){
		playerUnitLevel = Integer.parseInt(row[0]);
		AIUnitLevel = Integer.parseInt(row[1]);
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

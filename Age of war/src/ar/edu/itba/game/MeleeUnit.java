package ar.edu.itba.game;



import java.util.ArrayList;

import Observers.UnitObserver;

public class MeleeUnit extends Unit {
	private static ArrayList<Integer> MAX_HP = new ArrayList<Integer>();
	private static ArrayList<Double> ATTACK_SPEED = new ArrayList<Double>();
	private static ArrayList<Integer> ATTACK_RANGE = new ArrayList<Integer>();
	private static ArrayList<Integer> MOVEMENT_SPEED = new ArrayList<Integer>();
	private static ArrayList<Integer> DAMAGE = new ArrayList<Integer>();
	private static Element ELEMENT;
	private static int GOLD = 300;
	private static int COST = 250;
	private static int EXP = 70;
	
	private static UpgradeStatus playerUS = new UpgradeStatus();
	private static UpgradeStatus AIUS = new UpgradeStatus();
	
	public static ArrayList<Integer> getMAX_HP() {
		return MAX_HP;
	}

	public static ArrayList<Double> getATTACK_SPEED() {
		return ATTACK_SPEED;
	}

	public static ArrayList<Integer> getATTACK_RANGE() {
		return ATTACK_RANGE;
	}

	public static ArrayList<Integer> getMOVEMENT_SPEED() {
		return MOVEMENT_SPEED;
	}

	public static ArrayList<Integer> getDAMAGE() {
		return DAMAGE;
	}

	public static Element getELEMENT() {
		return ELEMENT;
	}
	
	public static UpgradeStatus getPlayerUS() {
		return playerUS;
	}

	public static UpgradeStatus getAIUS() {
		return AIUS;
	}

	public MeleeUnit(Player player){
		
		this.player = player;
		this.objective = null;
		this.cooldown = 0;
		this.gold = GOLD;
		this.cost = COST;
		this.exp = EXP;
		
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
			this.maxHp = MAX_HP.get((playerUS.getMaxHpIndex()));
			this.hp = this.maxHp;
			this.attackSpeed = ATTACK_SPEED.get((playerUS.getAttackSpeedIndex()));
			this.attackRange = ATTACK_RANGE.get(playerUS.getAttackRangeIndex());
			this.damage = DAMAGE.get(playerUS.getDamageIndex());
			this.element = new Element(100, Game.GROUND_HEIGHT, 3, 0, 100, 100, false);
			this.movementSpeed = MOVEMENT_SPEED.get(playerUS.getMovementSpeedIndex());
			this.dir = Side.LEFT;

		}else{
			this.maxHp = MAX_HP.get((AIUS.getMaxHpIndex()));
			this.hp = this.maxHp;
			this.attackSpeed = ATTACK_SPEED.get((AIUS.getAttackSpeedIndex()));
			this.attackRange = ATTACK_RANGE.get(AIUS.getAttackRangeIndex());
			this.damage = DAMAGE.get(AIUS.getDamageIndex());
			this.element = new Element(1000, Game.GROUND_HEIGHT, 3, 0, 100, 100, false);
			this.movementSpeed = MOVEMENT_SPEED.get(AIUS.getMovementSpeedIndex()) * (-1);
			this.dir = Side.RIGHT;
		}
		this.observer = new UnitObserver(this);
		this.addObserver(this.observer);
		WorldManager.getInstance().getElements().add(this.element);

	}
}

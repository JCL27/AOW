package ar.edu.itba.game;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Observers.PlayerObserver;
import Units.Unit;
import exceptions.NotEnoughExpException;
import exceptions.NotEnoughGoldException;
import exceptions.UnavailableUpgradeException;

public class Player implements Serializable{

	
	private static final long serialVersionUID = -3361130881364588943L;
	private Integer gold;
	private Integer experience;
	private ArrayList<Unit> units;
	private ArrayList<Projectile> projectiles;
	private Tower tower;
	private Base base;
	private ArrayList<Class<Unit>> unitsQueue;
	private boolean playerCreatingUnit = false;
	private int playerUnitCreationTime = 0;
	private transient PlayerObserver observer;
	private Class unitToQueue = null;
	private Side side;
	
	public Player (Side side, PlayerObserver playerObserver){
		this.gold = GameStats.INITIAL_GOLD;
		this.experience = 0;
		this.base = UnitFactory.getInstance().createBase(side);
		this.units = new ArrayList<Unit>();
		this.projectiles = new ArrayList<Projectile>();
		this.unitsQueue = new ArrayList<Class<Unit>>();
		this.observer = playerObserver;
		this.side = side;
	}
	
	public ArrayList<Projectile> getProjectiles(){
		return this.projectiles;
	}
	
	public ArrayList<Unit> getUnits(){
		return units;
	}
	
	public void research(Class upgradeType){
		this.research(upgradeType, null);
	}
	
	public void research(Class class1, Class class2){
		
		try {
			Upgrades.Upgrades.getInstance().applyUpgrade(class1.getSimpleName(), this, class2);
		} catch (UnavailableUpgradeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughExpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Side getSide() {
		return side;
	}

	public boolean buyUnit(Class unitClass){
		Integer unitCost = null;
		boolean created = true;
		boolean available = false;
		try {
			if(this.equals(WorldManager.getInstance().getPlayer()))
				available = (boolean) unitClass.getMethod("isPlayerAvailable").invoke(unitClass);
			else
				available = (boolean) unitClass.getMethod("isAIAvailable").invoke(unitClass);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e1) {
			e1.printStackTrace();
		}
		if(available && this.unitsQueue.size()<5){
			try {
				unitCost = (Integer)unitClass.getMethod("getCost", Player.class).invoke(null, this);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException| SecurityException e) {
				e.printStackTrace();
			}
			try{
				this.charge(unitCost);
				this.unitsQueue.add(unitClass);
				this.observer.addElementToQueue(this, unitClass);
			}catch(NotEnoughGoldException e){
				created = false;
				e.printStackTrace();	
			}
		}
		return created;
	}
	
	public void createUnit(Class unitClass){
		Unit unit = UnitFactory.getInstance().createUnit(unitClass, this);
		if(unit!=null){
			this.units.add(unit);
		}
	}
	
	public boolean buyTower(){
		boolean created = true;
		try {
			this.charge(GameStats.TOWER_COST);
			this.createTower();
		} catch (NotEnoughGoldException e) {
			created = false;
			e.printStackTrace();
		}
		return created;
	}
	
	public void createTower(){
		this.tower = UnitFactory.getInstance().createTower(this);
	}
	
	public void addGold(int gold){
		this.gold += gold;
	}
	
	public void charge(int gold) throws NotEnoughGoldException{
		if(this.gold < gold)
			throw new NotEnoughGoldException();
		this.gold -= gold;
	}
	
	public void useExp(int exp) throws NotEnoughExpException{
		if (this.experience < exp){
			System.out.println("Player: playerExp: " + this.experience + " cost: " + exp);
			throw new NotEnoughExpException();
		}
		this.experience -= exp;
	}
	
	public void addExp(int exp){
		this.experience += exp;
	}
	
	public ArrayList<Class<Unit>> getUnitsQueue() {
		return unitsQueue;
	}

	public Integer getGold(){
		return this.gold;
	}
	
	public Integer getExp(){
		return this.experience;
	}
	
	public Tower getTower(){
		return this.tower;
	}
	
	public void sellTower(){
		if(this.tower != null){
			Upgrades.Upgrades.getInstance().setUnavailable("TowerDamageUpgrade", this);
			Upgrades.Upgrades.getInstance().setUnavailable("TowerAttackSpeedUpgrade", this);
			Upgrades.Upgrades.getInstance().setUnavailable("TowerAttackRangeUpgrade", this);
			this.tower.Sell();
		}	
	}
	
	public Base getBase(){
		return this.base;
	}
	
	@Override
	public String toString() {
		return "Player [gold=" + gold + ", experience=" + experience
				+ ", units=" + units + ", projectiles=" + projectiles + "]";
	}

	public void disposeTower() {
		this.tower = null;
	}
	
	public void updateQueue(){
		if(!this.getUnitsQueue().isEmpty()){

			if(this.playerCreatingUnit && this.playerUnitCreationTime == 0){
				this.getUnitsQueue().remove(0);
				this.createUnit(unitToQueue);
				this.playerCreatingUnit = false;
				this.observer.removeElementFromQueue(this, 0);
			}else if(this.playerCreatingUnit){
				this.playerUnitCreationTime--;
				this.observer.updateCurrentTime(this, this.playerUnitCreationTime);
			}else{
				try {
					this.unitToQueue = this.getUnitsQueue().get(0);
					this.playerUnitCreationTime = (int) unitToQueue.getMethod("getCreationTime").invoke(null);
					this.playerCreatingUnit = true;
				} catch (IllegalAccessException | IllegalArgumentException| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Class getUnitToQueue() {
		return unitToQueue;
	}

	public int getPlayerUnitCreationTime() {
		return playerUnitCreationTime;
	}

	public void setObserver(PlayerObserver playerObserver) {
		this.observer = playerObserver;
		this.observer.loadQueue(this, this.unitsQueue, this.playerUnitCreationTime);
	}
}

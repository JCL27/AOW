package ar.edu.itba.game;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import exceptions.NotEnoughExpException;
import exceptions.NotEnoughGoldException;
import exceptions.UnavailableUpgradeException;
import Units.Unit;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3361130881364588943L;
	private int gold;
	private int experience;
	private ArrayList<Unit> units;
	private ArrayList<Projectile> projectiles;
	private Tower tower;
	private Base base;
	private ArrayList<Class> unitsQueue;
	
	public Player (Side side){
		this.gold = GameStats.INITIAL_GOLD;
		this.experience = 0;
		this.base = new Base(side);
		this.units = new ArrayList<Unit>();
		this.projectiles = new ArrayList<Projectile>();
		this.unitsQueue = new ArrayList<Class>();
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
	
	public void research(Class upgradeType, Class unitType){
		
		try {
			Upgrades.Upgrades.getInstance().applyUpgrade(upgradeType.getSimpleName(), this, unitType);
		} catch (UnavailableUpgradeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughExpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(available){
			try {
				unitCost = (Integer)unitClass.getMethod("getCost", Player.class).invoke(null, this);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException| SecurityException e) {
				e.printStackTrace();
			}
			try{
				this.charge(unitCost);
				this.unitsQueue.add(unitClass);
			}catch(NotEnoughGoldException e){
				created = false;
				e.printStackTrace();	
			}
		}
		return created;
	}
	
	
	public void createUnit(Class unitClass){
		Unit unit = null;
		try {
			unit = (Unit) unitClass.getConstructor(Player.class).newInstance(this);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(unit!=null){
			this.units.add(unit);
			//this.unitsQueue.add(unitClass);
			//this.BuyUnit(unitClass);
		}
		//WorldManager.getInstance().getAI().getUnits().add(unit2);
		//WorldManager.getInstance().getAI().getUnitsQueue().add(unit2);
		
		// TODO Auto-generated catch block
		

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
		this.tower = new Tower(this);
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
	
	public ArrayList<Class> getUnitsQueue() {
		return unitsQueue;
	}

	public int getGold(){
		return this.gold;
	}
	
	public int getExp(){
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
	
	
}

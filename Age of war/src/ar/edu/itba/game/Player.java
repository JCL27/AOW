package ar.edu.itba.game;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import exceptions.NotEnoughGoldException;
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
		this.gold = WorldManager.INITIAL_GOLD;
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
	
	public void buyUnit(Class unitClass){
		Integer unitCost = null;
		
		try {
			unitCost = (Integer)unitClass.getMethod("getCost", Player.class).invoke(null, this);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(unitClass.getSimpleName()+ " " + unitCost);
		try{
			this.charge(unitCost);
			this.unitsQueue.add(unitClass);
		}catch(NotEnoughGoldException e){
			e.printStackTrace();	
		}
		System.out.println("gold: " + this.gold);
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
	
	public void buyTower(){
		try {
			System.out.println("ORO ANTES: " + this.gold);
			this.charge(GameStats.TOWER_COST);
			this.createTower();
			System.out.println("ORO DESPUES: " + this.gold);
		} catch (NotEnoughGoldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

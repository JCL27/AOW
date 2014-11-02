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
			//this.unitsQueue.add(unit);
		}
		//WorldManager.getInstance().getAI().getUnits().add(unit2);
		//WorldManager.getInstance().getAI().getUnitsQueue().add(unit2);

	}
	
	public void CreateTower(Class towerClass){
		this.tower = null;
		Tower tower2 = null;
		try {
			this.tower = (Tower) towerClass.getConstructor(Player.class).newInstance(this);
			tower2 = (Tower) towerClass.getConstructor(Player.class).newInstance(WorldManager.getInstance().getplayerAI());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		WorldManager.getInstance().getplayerAI().tower = tower2;
		
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
	
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	public void sellTower(){
		if(this.tower != null)
			this.tower.Sell();
		
	}

	public Base getBase(){
		return this.base;
	}
	//	public Unit(int maxHp, double attackSpeed, int attackRange, int movementSpeed, int damage, Element element, Direction dir){

	@Override
	public String toString() {
		return "Player [gold=" + gold + ", experience=" + experience
				+ ", units=" + units + ", projectiles=" + projectiles + "]";
	}
	
	
}

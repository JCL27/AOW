package ar.edu.itba.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import exceptions.NotEnoughGoldException;
import Units.Unit;

public class Player {
	private int gold;
	private int experience;
	private ArrayList<Unit> units;
	private ArrayList<Projectile> projectiles;
	private Tower tower;
	private Base base;
	
	public Player (Side side){
		this.gold = WorldManager.INITIAL_GOLD;
		this.experience = 0;
		this.base = new Base(side);
		this.units = new ArrayList<Unit>();
		this.projectiles = new ArrayList<Projectile>();
	}
	
	public ArrayList<Projectile> getProjectiles(){
		return this.projectiles;
	}
	
	public ArrayList<Unit> getUnits(){
		return units;
	}
	
	public void createUnit(Class unitClass){
		Unit unit = null;
		Unit unit2 = null;
		try {
			unit = (Unit) unitClass.getConstructor(Player.class).newInstance(this);
			unit2 = (Unit) unitClass.getConstructor(Player.class).newInstance(WorldManager.getInstance().getAI());
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
		}
		WorldManager.getInstance().getAI().getUnits().add(unit2);
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
	
	public int getGold(){
		return this.gold;
	}
	
	public int getExp(){
		return this.experience;
	}
	public Tower getTower(){
		return this.tower;
	}
	
	public Base getBase(){
		return this.base;
	}
	//	public Unit(int maxHp, double attackSpeed, int attackRange, int movementSpeed, int damage, Element element, Direction dir){

	
}

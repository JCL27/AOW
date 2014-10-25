package ar.edu.itba.game;

import java.util.ArrayList;

public class Player {
	private int gold;
	private int experience;
	private Base base;
	private ArrayList<Unit> units;
	private ArrayList<Projectile> projectiles;
	private Tower tower;
	
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
	
	public void createUnit(){
		this.units.add(new MeleeUnit(this));
		WorldManager.getInstance().getAI().getUnits().add(new MeleeUnit(WorldManager.getInstance().getAI()));
	}
	
	public void addGold(int gold){
		this.gold += gold;
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
	//	public Unit(int maxHp, double attackSpeed, int attackRange, int movementSpeed, int damage, Element element, Direction dir){

	
}

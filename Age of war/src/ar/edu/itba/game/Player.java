package ar.edu.itba.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player {
	private int gold;
	private int experience;
	private Base base;
	private ArrayList<Unit> units;
	private ArrayList<Projectile> projectiles;
	private Tower tower;
	private WorldManager WM;
	
	public Player (WorldManager WM){
		this.gold = 0;
		this.experience = 0;
		this.base = new Base();
		this.units = new ArrayList<Unit>();
		this.projectiles = new ArrayList<Projectile>();
		this.WM = WM;
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
	//	public Unit(int maxHp, double attackSpeed, int attackRange, int movementSpeed, int damage, Element element, Direction dir){

	
}

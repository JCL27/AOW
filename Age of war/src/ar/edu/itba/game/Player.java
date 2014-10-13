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
		this.WM = WM;
	}
	
	public ArrayList<Projectile> getProjectiles(){
		return this.projectiles;
	}
	
	public ArrayList<Unit> getUnits(){
		return units;
	}
	
	public void createUnit(){
		Texture texture = new Texture(Gdx.files.classpath("resources/green button.png"));
		Element elem = new Element(texture, 0, 0, 3, 0, 6, false);
		units.add(new Unit(500, 5.4, 200, 2, 120, elem, Side.LEFT));
		elem = new Element(texture, 1000, 0, 3, 0, 6, false);
		WorldManager.getInstance().getAI().getUnits().add(new Unit(500, 4.0, 200, -1, 160, elem, Side.RIGHT));

	}
	//	public Unit(int maxHp, double attackSpeed, int attackRange, int movementSpeed, int damage, Element element, Direction dir){

	
}

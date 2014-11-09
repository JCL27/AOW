package ar.edu.itba.game.backend.logic;

import java.io.Serializable;
import java.util.Observable;

import ar.edu.itba.game.frontend.observers.BaseObserver;

public class Base extends Observable implements Attackable, Serializable {

	private static final long serialVersionUID = -4883608043561346759L;

	static int HEIGHT = 282;
	static int WIDTH = 180;
	
	private int maxHP;
	private int HP;
	private Element element;
	private Side side;
	private transient BaseObserver observer;
	
	public Base(Side side, BaseObserver	baseObserver){
		int X;
		this.side = side;
		if(side==Side.LEFT){
			X = -10 + 76/2;
		}else{
			X = Game.WIDTH * Game.SCALE - WIDTH + 10 + 76/2;
		}
		element = new Element(X, Game.GROUND_HEIGHT, WIDTH, HEIGHT);
		this.maxHP = GameStats.BASE_MAX_HP;
		this.HP = GameStats.BASE_MAX_HP;
		this.observer = baseObserver;
	}
	
	public int getHP() {
		return HP;
	}
	
	//TODO: ADD THROWS ENDGAMEEXCEPTION A LA INTERFAZ
	/**
	 * Reduces the base hp and ends the game in case the base hp runs out
	 */
	@Override
	public void receiveDamage(int damage){
			this.HP -=damage;
			this.observer.update(this);
			if(this.HP <= 0){
				this.observer.setLooser(this.side);
				WorldManager.getInstance().endGame();
			}
	}
	
	public int getMaxHP() {
		return maxHP;
	}

	@Override
	public Element getElement() {
		return this.element;
	}

	@Override
	public boolean doesFly() {
		return false;
	}

	public float getX() {
		if(this.side == Side.LEFT)
			return this.element.getX() -38;
		return this.element.getX() - 3 * 38;
	}
	
	public float getY() {
		return this.element.getY() - 2;
	}

	public int getHeight() {
		return this.element.getHeight();
	}
	
	public int getWidth() {
		return this.element.getWidth();
	}

	public Side getSide() {
		return this.side;
	}

	public void setObserver(BaseObserver baseObserver) {
		this.observer = baseObserver;
		this.observer.update(this);
	}
	
	
}

package ar.edu.itba.game;

import java.io.Serializable;
import java.util.Observable;

import Observers.BaseObserver;
import Observers.UnitObserver;
import exceptions.DeadUnitException;
import exceptions.EndGameException;

public class Base extends Observable implements Attackable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4883608043561346759L;
	static int MAX_HIT_POINTS = 1000;
	static int HEIGHT = 282;
	static int WIDTH = 180;
	
	private int maxHP;
	private int HP;
	private Element element;
	private Side side;
	private BaseObserver observer;
	
	public Base(Side side){
		int X;
		this.side = side;
		if(side==Side.LEFT){
			X = -10 + 76/2;
		}else{
			X = Game.WIDTH * Game.SCALE - WIDTH + 10 + 76/2;
		}
		element = new Element(X, Game.GROUND_HEIGHT, WIDTH, HEIGHT);
		this.maxHP = MAX_HIT_POINTS;
		this.HP = MAX_HIT_POINTS;
		
		this.addObserver(new BaseObserver(this));
	}
	
	//TODO: ADD THROWS ENDGAMEEXCEPTION A LA INTERFAZ
	@Override
	public void receiveDamage(int damage) throws DeadUnitException {
			System.out.println("HP: " + this.HP);
			this.HP -=damage;
			if(this.HP <= 0){
				try {
					throw new EndGameException();
				} catch (EndGameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		
	}
	
	@Override
	public Element getElement() {
		// TODO Auto-generated method stub
		return this.element;
	}

	@Override
	public boolean doesFly() {
		// TODO Auto-generated method stub
		return false;
	}

	public float getX() {
		if(this.side == Side.LEFT)
			return this.element.getX() -76/2;
		return this.element.getX() - 3 * 76/2;
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
	
	public void reAssignObserver(){
		this.deleteObservers();
		this.observer = new BaseObserver(this);
		this.addObserver(this.observer);
	}
	
	
}

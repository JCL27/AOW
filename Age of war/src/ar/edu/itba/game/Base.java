package ar.edu.itba.game;

import java.util.Observable;

import Observers.BaseObserver;
import exceptions.DeadUnitException;
import exceptions.EndGameException;

public class Base extends Observable implements Attackable {

	static int MAX_HIT_POINTS = 1000;
	static int HEIGHT = 282;
	static int WIDTH = 180;
	
	private int maxHP;
	private int HP;
	private Element element;
	private Side side;
	
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
	
	@Override
	public void receiveDamage(int damage) throws DeadUnitException {
		try{
			System.out.println("HP: " + this.HP);
			this.HP -=damage;
			if(this.HP <= 0){
				throw new EndGameException();
			}
		}catch(EndGameException e){
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
	
	
	
}

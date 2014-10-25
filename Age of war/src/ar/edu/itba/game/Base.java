package ar.edu.itba.game;

import exceptions.DeadUnitException;
import exceptions.EndGameException;

public class Base implements Attackable {

	static int MAX_HIT_POINTS = 1000;
	static int HEIGHT = 500;
	static int WIDTH = 500;
	
	private int maxHP;
	private int HP;
	private Element element;
	
	public Base(Side side){
		int X;
		if(side==Side.LEFT){
			X = 50;
		}else{
			X = 1000;
		}
		element = new Element(X, Game.GROUND_HEIGHT, HEIGHT, WIDTH );
		this.maxHP = MAX_HIT_POINTS;
		this.HP = MAX_HIT_POINTS;
	}
	
	@Override
	public void receiveDamage(int damage) throws DeadUnitException {
		try{
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
		return null;
	}
	
}

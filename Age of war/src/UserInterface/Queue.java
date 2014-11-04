package UserInterface;

import java.util.ArrayList;

import ar.edu.itba.game.Player;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.WorldManager;
import Draws.Drawable;

public class Queue implements DrawableObject {
	private ArrayList<QueueElement> elemsVec = new ArrayList<QueueElement>();
	private ArrayList<Drawable> draws = new ArrayList<Drawable>();
	private Player player;
	private float xPos;
	private final float yPos = 80f;
	private static final int SEPARATION = 60;
	private Side side;
	
	private int count = 0;
	
	public Queue(Side side){
		this.side = side;
		if(this.side == Side.LEFT){
			this.xPos = 50;
		}else{
			this.xPos = 900;
		}
	}
	
	@Override
	public ArrayList<Drawable> getDraws() {
		return this.draws;
	}
	
	public void updateTime(int current){
		this.elemsVec.get(0).setCurrent(current);
	}
	
	public void addElement(Class unitClass, int creationTime){
		QueueElement elem = new QueueElement(this.xPos + SEPARATION * this.count++, this.yPos, unitClass, creationTime);
		this.elemsVec.add(elem);
		this.draws.addAll(elem.getDraws());
	}
	
	public void removeElement(int index){
		if(index > 4){
			throw new ArrayIndexOutOfBoundsException();
		}
		count--;
		this.draws.removeAll(this.elemsVec.get(index).getDraws());
		this.elemsVec.remove(index);
		for(QueueElement elem:this.elemsVec){
			elem.setxPos(elem.getxPos() - SEPARATION);
		}
	}
	
}

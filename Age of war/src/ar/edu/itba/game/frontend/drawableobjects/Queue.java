package DrawableObjects;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Draws.Drawable;
import Units.Unit;
import ar.edu.itba.game.Side;

public class Queue implements DrawableObject {
	private ArrayList<QueueElement> elemsVec = new ArrayList<QueueElement>();
	private ArrayList<Drawable> draws = new ArrayList<Drawable>();
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

	@Override
	public void setCurrent(int current, float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrent(int current){
		if(this.elemsVec.size()!=0)
			this.elemsVec.get(0).setCurrent(current);
	}

	public void loadQueue(ArrayList<Class<Unit>> unitsQueue,
			int playerUnitCreationTime) {
		this.elemsVec.clear();
		this.draws.clear();
		this.count = 0;
		for(Class<Unit> unit:unitsQueue){
			try {
				int creationTime = (int) unit.getMethod("getCreationTime").invoke(null);
				this.addElement(unit, creationTime);
			} catch (IllegalAccessException | IllegalArgumentException| InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				e.printStackTrace();
			}	
		}
		this.setCurrent(playerUnitCreationTime);
		
	}
	
}

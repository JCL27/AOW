package Observers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import UserInterface.Queue;
import UserInterface.UIManager;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.WorldManager;

public class PlayerObserver implements Observer {

	private Side side;
	private Player player;
	private Queue queue;
	ArrayList<Class> unitsQueue; 
	
	public PlayerObserver(Player player, Side side){
		this.side = side;
		if(side.equals(Side.LEFT))
			queue = new Queue(this.side);
		else
			queue = new Queue(this.side);
		UIManager.getInstance().getDOs().add(queue);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(this.player == null){
			if(this.side == Side.LEFT)
				this.player = WorldManager.getInstance().getPlayer();
			else
				this.player = WorldManager.getInstance().getplayerAI();
		}
		int creationTime =	this.player.getPlayerUnitCreationTime();
		this.queue.addElement(this.player.getUnitToQueue(), creationTime);
	}
	
	public void addElementToQueue(Class unitClass){
		if(this.player == null){
			if(this.side == Side.LEFT)
				this.player = WorldManager.getInstance().getPlayer();
			else
				this.player = WorldManager.getInstance().getplayerAI();
		}
		int creationTime;
		try {
			creationTime = (int) unitClass.getMethod("getCreationTime").invoke(null);
			this.queue.addElement(unitClass, creationTime);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}

	}
	
	public void updateCurrentTime(int current){
		this.queue.updateTime(current);
	}
	
	public void removeElementFromQueue(int index){
		this.queue.removeElement(index);
	}
	
	

}

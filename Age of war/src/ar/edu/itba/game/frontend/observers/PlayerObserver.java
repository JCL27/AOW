package ar.edu.itba.game.frontend.observers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.backend.units.Unit;
import ar.edu.itba.game.frontend.drawableobjects.Queue;
import ar.edu.itba.game.frontend.userinterface.UIManager;

/**
 * 
 * Tracks the players queues and update their visual representation
 *
 */
public class PlayerObserver{
	
	public PlayerObserver(){

	}
	
	/**
	 * Add a new queue element at the end of the queue
	 * @param player
	 * @param unitClass
	 */
	public void addElementToQueue(Player player, Class unitClass){
		int creationTime;
		Side side = player.getSide();
		try {
			creationTime = (int) unitClass.getMethod("getCreationTime").invoke(null);
			if(side.equals(Side.LEFT))
				UIManager.getInstance().getPlayerQueue().addElement(unitClass, creationTime);
			else
				UIManager.getInstance().getAIQueue().addElement(unitClass, creationTime);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Updates the remaining time
	 * @param player
	 * @param current
	 */
	public void updateCurrentTime(Player player, int current){
		if(player.getSide().equals(Side.LEFT))
			UIManager.getInstance().getPlayerQueue().setCurrent(current);
		else
			UIManager.getInstance().getAIQueue().setCurrent(current);
	}
	
	/**
	 * Removes an element from the queue
	 * @param player
	 * @param index
	 */
	public void removeElementFromQueue(Player player, int index){
		if(player.getSide().equals(Side.LEFT))
			UIManager.getInstance().getPlayerQueue().removeElement(index);
		else
			UIManager.getInstance().getAIQueue().removeElement(index);
	}
	/**
	 * Drop the current queues and reassign their elements to be accord with current players queues
	 * @param player
	 * @param unitsQueue
	 * @param playerUnitCreationTime
	 */
	public void loadQueue(Player player, ArrayList<Class<Unit>> unitsQueue,
			int playerUnitCreationTime) {
		Queue queue;
		if(player.getSide().equals(Side.LEFT)){
			queue = UIManager.getInstance().getPlayerQueue();
		}else
			queue = UIManager.getInstance().getAIQueue();
		queue.loadQueue(unitsQueue, playerUnitCreationTime);
	}
	
	

}

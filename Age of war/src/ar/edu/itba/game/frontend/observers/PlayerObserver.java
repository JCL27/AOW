package ar.edu.itba.game.frontend.observers;

import java.util.ArrayList;

import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.backend.units.AntiaircraftUnit;
import ar.edu.itba.game.backend.units.FlyingUnit;
import ar.edu.itba.game.backend.units.MeleeUnit;
import ar.edu.itba.game.backend.units.RangedUnit;
import ar.edu.itba.game.backend.units.UnitType;
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
	 * @param type
	 */
	public void addElementToQueue(Player player, UnitType type){
		int creationTime = 0;
		Side side = player.getSide();
		switch(type){
		case MELEE_UNIT:
			creationTime = MeleeUnit.getCreationTime();
			break;
		case RANGED_UNIT:
			creationTime = RangedUnit.getCreationTime();
			break;
		case FLYING_UNIT:
			creationTime = FlyingUnit.getCreationTime();
			break;
		case ANTIAIRCRAFT_UNIT:
			creationTime = AntiaircraftUnit.getCreationTime();
			break;
		}
		if(side.equals(Side.LEFT))
			UIManager.getInstance().getPlayerQueue().addElement(type, creationTime);
		else
			UIManager.getInstance().getAIQueue().addElement(type, creationTime);
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
	public void loadQueue(Player player, ArrayList<UnitType> unitsQueue,
			int playerUnitCreationTime) {
		Queue queue;
		if(player.getSide().equals(Side.LEFT)){
			queue = UIManager.getInstance().getPlayerQueue();
		}else
			queue = UIManager.getInstance().getAIQueue();
		queue.loadQueue(unitsQueue, playerUnitCreationTime);
	}
	
	

}

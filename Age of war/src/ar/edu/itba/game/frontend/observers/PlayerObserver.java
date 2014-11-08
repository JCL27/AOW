package Observers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import DrawableObjects.Queue;
import Units.Unit;
import UserInterface.UIManager;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Side;

public class PlayerObserver{
	
	public PlayerObserver(){

	}
	
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
	
	public void updateCurrentTime(Player player, int current){
		if(player.getSide().equals(Side.LEFT))
			UIManager.getInstance().getPlayerQueue().setCurrent(current);
		else
			UIManager.getInstance().getAIQueue().setCurrent(current);
	}
	
	public void removeElementFromQueue(Player player, int index){
		if(player.getSide().equals(Side.LEFT))
			UIManager.getInstance().getPlayerQueue().removeElement(index);
		else
			UIManager.getInstance().getAIQueue().removeElement(index);
	}

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

package Observers;

import java.util.Observable;
import java.util.Observer;

import Units.MeleeUnit;
import UserInterface.QueueElement;
import UserInterface.UIManager;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.WorldManager;

public class PlayerObserver implements Observer {

	private Player player;
	private QueueElement queueElem;
	
	public PlayerObserver(Player player, Side side){
		this.player = player;
		if(side.equals(Side.LEFT))
			queueElem = new QueueElement(50, 50, MeleeUnit.class, 50);
		else
			queueElem = new QueueElement(900, 50, MeleeUnit.class, 50);
		UIManager.getInstance().getDOs().add(queueElem);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.player.getPlayerUnitCreationTime();
	}

}

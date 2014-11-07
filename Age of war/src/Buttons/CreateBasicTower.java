package Buttons;

import Draws.Icon;
import Draws.Textures;
import ar.edu.itba.game.WorldManager;

public class CreateBasicTower extends Button {
	
	public CreateBasicTower(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_RANGE_UPGRADE_ICON, Textures.DARK_TOWER_RANGE_UPGRADE_ICON);
	}

	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().buyTower();
		//WorldManager.getInstance().getplayerAI().CreateTower();
	}
	

}

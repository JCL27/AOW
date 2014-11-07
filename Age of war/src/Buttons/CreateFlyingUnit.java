package Buttons;

import Draws.Icon;
import Draws.Textures;
import Units.FlyingUnit;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class CreateFlyingUnit extends Button {

	public CreateFlyingUnit(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.FLYING_UNIT_ICON, Textures.DARK_FLYING_UNIT_ICON);
	}

	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();	
		if(player.getGold()<FlyingUnit.getCost(player) || !FlyingUnit.isPlayerAvailable()){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}

	
	@Override
	public void Click() {
		// TODO Auto-generated method stub
		//WorldManager.getInstance().getPlayer().createUnit(FlyingUnit.class);
		WorldManager.getInstance().getPlayer().buyUnit(FlyingUnit.class);
		//WorldManager.getInstance().getplayerAI().buyUnit(FlyingUnit.class);
	}

}

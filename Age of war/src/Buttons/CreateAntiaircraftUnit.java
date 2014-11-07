package Buttons;

import Draws.Icon;
import Draws.Textures;
import Units.AntiaircraftUnit;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class CreateAntiaircraftUnit extends Button {


	
	public CreateAntiaircraftUnit(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.ANTIAIRCRAFT_UNIT_ICON, Textures.DARK_ANTIAIRCRAFT_UNIT_ICON);
	}
	
	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if(player.getGold()<AntiaircraftUnit.getCost(player) || !AntiaircraftUnit.isPlayerAvailable()){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().buyUnit(AntiaircraftUnit.class);
	}
}

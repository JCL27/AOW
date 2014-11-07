package Buttons;

import Draws.Icon;
import Draws.Textures;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class DisposeTower extends Button{

	public DisposeTower(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.SELL_TOWER_ICON, Textures.DARK_SELL_TOWER_ICON);
	}

	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if(player.getTower()==null){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}

	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().sellTower();
	}

}

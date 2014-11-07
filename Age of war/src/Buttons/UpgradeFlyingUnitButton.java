package Buttons;

import Draws.Icon;
import Draws.Textures;
import Units.FlyingUnit;
import Upgrades.UnitUpgrade;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class UpgradeFlyingUnitButton  extends Button{
	public UpgradeFlyingUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.FLYING_UNIT_UPGRADE_ICON, Textures.DARK_FLYING_UNIT_UPGRADE_ICON);
	}

	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if(player.getExp()<GameStats.UNIT_UPGRADE_COST){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}



	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(UnitUpgrade.class, FlyingUnit.class);
	}
}

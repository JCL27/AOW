package Buttons;

import Draws.Icon;
import Draws.Textures;
import Upgrades.FlyingUnitResearch;
import ar.edu.itba.game.WorldManager;

public class ResearchFlyingUnitsButton  extends Button{
	public ResearchFlyingUnitsButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_DAMAGE_UPGRADE_ICON, Textures.DARK_TOWER_DAMAGE_UPGRADE_ICON);
	}
	
	@Override
	public void Click() {
		System.out.println("investiga fly");
		WorldManager.getInstance().getPlayer().research(FlyingUnitResearch.class);


	}
}

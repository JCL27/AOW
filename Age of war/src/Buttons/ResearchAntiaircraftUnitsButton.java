package Buttons;

import Draws.Icon;
import Draws.Textures;
import Upgrades.AntiaircraftUnitResearch;
import ar.edu.itba.game.WorldManager;


public class ResearchAntiaircraftUnitsButton  extends Button{
	public ResearchAntiaircraftUnitsButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_DAMAGE_UPGRADE_ICON, Textures.DARK_TOWER_DAMAGE_UPGRADE_ICON);
	}
	
	@Override
	public void Click() {
		System.out.println("investiga anti");

		WorldManager.getInstance().getPlayer().research(AntiaircraftUnitResearch.class);
	}
}

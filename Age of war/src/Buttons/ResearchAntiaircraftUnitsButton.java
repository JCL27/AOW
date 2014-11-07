package Buttons;

import Draws.Icon;
import Draws.Textures;
import Upgrades.AntiaircraftUnitResearch;
import ar.edu.itba.game.WorldManager;


public class ResearchAntiaircraftUnitsButton  extends Button{
	public ResearchAntiaircraftUnitsButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.ANTIAIRCRAFT_UNIT_RESEARCH_ICON, Textures.DARK_ANTIAIRCRAFT_UNIT_RESEARCH_ICON);
	}
	
	@Override
	public void Click() {
		System.out.println("investiga anti");

		WorldManager.getInstance().getPlayer().research(AntiaircraftUnitResearch.class);
	}
}

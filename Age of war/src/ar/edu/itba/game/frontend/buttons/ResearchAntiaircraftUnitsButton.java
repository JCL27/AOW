package ar.edu.itba.game.frontend.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.AntiaircraftUnit;
import ar.edu.itba.game.backend.upgrades.AntiaircraftUnitResearch;
import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;


public class ResearchAntiaircraftUnitsButton  extends Button{
	public ResearchAntiaircraftUnitsButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.ANTIAIRCRAFT_UNIT_RESEARCH_ICON, Textures.DARK_ANTIAIRCRAFT_UNIT_RESEARCH_ICON);
	}
	
	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if(player.getExp()<GameStats.ANTIAIRCRAFT_UNIT_RESEARCH_COST || AntiaircraftUnit.isPlayerAvailable()){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Research Antiaircraft Units", this.draw.getxPos(), this.draw.getyPos());
	}

	@Override
	public void Click() {
		System.out.println("investiga anti");

		WorldManager.getInstance().getPlayer().research(AntiaircraftUnitResearch.class);
	}
}

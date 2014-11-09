package ar.edu.itba.game.frontend.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.FlyingUnit;
import ar.edu.itba.game.backend.upgrades.FlyingUnitResearch;
import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;

public class ResearchFlyingUnitsButton  extends Button{
	public ResearchFlyingUnitsButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.FLYING_UNIT_ICON, Textures.DARK_FLYING_UNIT_ICON);
	}
	
	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if(player.getExp()<GameStats.FLYING_UNIT_RESEARCH_COST || FlyingUnit.isPlayerAvailable()){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Research Flying Units", this.draw.getxPos(), this.draw.getyPos());
	}

	@Override
	public void Click() {
		System.out.println("investiga fly");
		WorldManager.getInstance().getPlayer().research(FlyingUnitResearch.class);


	}
}

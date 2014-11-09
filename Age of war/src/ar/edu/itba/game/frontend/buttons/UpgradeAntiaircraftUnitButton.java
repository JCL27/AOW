package ar.edu.itba.game.frontend.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.AntiaircraftUnit;
import ar.edu.itba.game.backend.upgrades.UnitUpgrade;
import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;

public class UpgradeAntiaircraftUnitButton extends Button {
	public UpgradeAntiaircraftUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.ANTIAIRCRAFT_UNIT_UPGRADE_ICON, Textures.DARK_ANTIAIRCRAFT_UNIT_UPGRADE_ICON);
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
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Upgrade Antiaircraft", this.draw.getxPos(), this.draw.getyPos());
	}

	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(UnitUpgrade.class, AntiaircraftUnit.class);
	}
}

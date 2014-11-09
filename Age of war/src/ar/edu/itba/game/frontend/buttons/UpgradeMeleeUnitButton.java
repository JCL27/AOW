package ar.edu.itba.game.frontend.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.MeleeUnit;
import ar.edu.itba.game.backend.upgrades.UnitUpgrade;
import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;

public class UpgradeMeleeUnitButton extends Button{
	public UpgradeMeleeUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.MELEE_UNIT_UPGRADE_ICON, Textures.DARK_MELEE_UNIT_UPGRADE_ICON);
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
		this.font.draw(SB, "Upgrade Melee", this.draw.getxPos(), this.draw.getyPos());
	}


	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(UnitUpgrade.class, MeleeUnit.class);
	}
}

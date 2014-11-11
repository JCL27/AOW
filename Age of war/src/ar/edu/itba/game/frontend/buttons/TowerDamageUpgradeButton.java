package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.upgrades.UpgradeType;
import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerDamageUpgradeButton extends Button {

	public TowerDamageUpgradeButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_DAMAGE_UPGRADE_ICON, Textures.DARK_TOWER_DAMAGE_UPGRADE_ICON);
	}

	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if(player.getExp()<GameStats.TOWER_DAMAGE_UPGRADE_COST ||
				(player.getTower()==null) || player.getTower().isUpgradedDamage()){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Upgrade Attack Damage", this.draw.getxPos(), this.draw.getyPos());
	}

	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().research(UpgradeType.TOWER_DAMAGE_UPGRADE);
	}
}

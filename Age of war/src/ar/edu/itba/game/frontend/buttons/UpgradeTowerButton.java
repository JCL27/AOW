package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;
import ar.edu.itba.game.frontend.userinterface.GameUIState;
import ar.edu.itba.game.frontend.userinterface.UIManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UpgradeTowerButton extends Button{
	public UpgradeTowerButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_DAMAGE_UPGRADE_ICON, Textures.DARK_TOWER_DAMAGE_UPGRADE_ICON);
	}
	
	public void showMessage(SpriteBatch SB){
			this.font.draw(SB, "Tower Upgrades", this.draw.getxPos(), this.draw.getyPos());
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.UPGRADE_TOWER);
		UIManager.getInstance().updateButtons();
	}
}

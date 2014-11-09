package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;
import ar.edu.itba.game.frontend.userinterface.GameUIState;
import ar.edu.itba.game.frontend.userinterface.UIManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UpgradeUnitButton extends Button {

	public UpgradeUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.UNIT_UPGRADE_ICON, Textures.UNIT_UPGRADE_ICON);
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Unit Upgrades", this.draw.getxPos(), this.draw.getyPos());
	}
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.UPGRADE_UNIT);
		UIManager.getInstance().updateButtons();
	}
}

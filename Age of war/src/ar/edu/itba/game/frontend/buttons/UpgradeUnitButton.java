package Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Draws.Icon;
import Draws.Textures;
import UserInterface.GameUIState;
import UserInterface.UIManager;

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

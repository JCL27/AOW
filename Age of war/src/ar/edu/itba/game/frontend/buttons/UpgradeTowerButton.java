package Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Draws.Icon;
import Draws.Textures;
import UserInterface.GameUIState;
import UserInterface.UIManager;

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

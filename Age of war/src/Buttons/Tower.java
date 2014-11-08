package Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Draws.Icon;
import Draws.Textures;
import UserInterface.GameUIState;
import UserInterface.UIManager;

public class Tower extends Button{

	public Tower(float X, float Y){
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWERS_ICON);
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Tower", this.draw.getxPos(), this.draw.getyPos());
	}
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.TOWER);
		UIManager.getInstance().updateButtons();
	}
	
}

package Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Draws.Icon;
import Draws.Textures;
import UserInterface.GameUIState;
import UserInterface.UIManager;


public class CreateUnit extends Button {

	public CreateUnit(float X, float Y){
		this.draw = new Icon(X, Y, 80, 80, Textures.CREATE_UNITS_ICON);
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Units", this.draw.getxPos(), this.draw.getyPos());
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.CREATE_UNIT);
		UIManager.getInstance().updateButtons();
	}
}

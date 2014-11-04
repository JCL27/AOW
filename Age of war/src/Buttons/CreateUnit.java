package Buttons;

import Draws.BigGreenButtonDraw;
import Draws.Icon;
import Draws.Textures;
import UserInterface.GameUIState;
import UserInterface.UIManager;


public class CreateUnit extends Button {

	public CreateUnit(float X, float Y){
		this.draw = new Icon(X, Y, 80, 80, Textures.CREATE_UNITS_ICON);
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.CREATE_UNIT);
		UIManager.getInstance().updateButtons();
	}
}

package Buttons;

import Draws.BigGreenButtonDraw;
import UserInterface.GameUIState;
import UserInterface.UIManager;


public class CreateUnit extends Button {

	public CreateUnit(float X, float Y){
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.CREATE_UNIT);
		UIManager.getInstance().updateButtons();
	}
}

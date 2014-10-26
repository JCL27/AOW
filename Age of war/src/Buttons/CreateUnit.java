package Buttons;

import Draws.BigGreenButtonDraw;
import UserInterface.UIManager;
import UserInterface.UIState;

public class CreateUnit extends Button {

	public CreateUnit(float X, float Y){
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().pushState(UIState.CREATE_UNIT);
		UIManager.getInstance().updateButtons();
	}
}

package Buttons;

import Draws.BigGreenButtonDraw;
import UserInterface.UIManager;
import UserInterface.UIState;

public class Tower extends Button{

	public Tower(float X, float Y){
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().pushState(UIState.TOWER);
		UIManager.getInstance().updateButtons();
	}
	
}

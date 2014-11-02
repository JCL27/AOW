package Buttons;

import Draws.BigGreenButtonDraw;
import UserInterface.UIManager;
import UserInterface.GameUIState;

public class Tower extends Button{

	public Tower(float X, float Y){
		this.draw = new BigGreenButtonDraw(X, Y);
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.TOWER);
		UIManager.getInstance().updateButtons();
	}
	
}

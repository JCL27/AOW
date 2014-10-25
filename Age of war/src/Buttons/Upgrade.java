package Buttons;

import Draws.BigGreenButtonDraw;
import UserInterface.UIManager;
import UserInterface.UIState;

public class Upgrade extends Button {

	@Override
	public void Click() {
		UIManager.getInstance().pushState(UIState.UPGRADES);
		UIManager.getInstance().updateButtons();
	}
	
	public Upgrade(float X, float Y){
		this.draw = new BigGreenButtonDraw(X, Y);
	}
}

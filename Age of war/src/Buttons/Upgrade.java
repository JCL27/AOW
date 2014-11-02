package Buttons;

import Draws.BigGreenButtonDraw;
import UserInterface.GameUIState;
import UserInterface.UIManager;

public class Upgrade extends Button {

	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.UPGRADES);
		UIManager.getInstance().updateButtons();
	}
	
	public Upgrade(float X, float Y){
		this.draw = new BigGreenButtonDraw(X, Y);
	}
}

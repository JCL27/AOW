package Buttons;

import Draws.Icon;
import Draws.Textures;
import UserInterface.GameUIState;
import UserInterface.UIManager;

public class Tower extends Button{

	public Tower(float X, float Y){
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWERS_ICON);
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.TOWER);
		UIManager.getInstance().updateButtons();
	}
	
}

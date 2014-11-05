package Buttons;

import Draws.ArrowDraw;
import UserInterface.UIManager;

public class Back extends Button{

	public Back(float X, float Y) {
		this.draw = new ArrowDraw(X, Y);
	}

	@Override
	public void Click() {
		UIManager.getInstance().popState();
		UIManager.getInstance().updateButtons();
	}
}

package Buttons;

import UserInterface.UIManager;
import Draws.ArrowDraw;

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

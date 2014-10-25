package Buttons;

import UserInterface.UIManager;
import UserInterface.UIState;

public class CreateUnit extends Button {

	@Override
	public void Click() {
		UIManager.getInstance().setState(UIState.CREATE_UNIT);
	}
}

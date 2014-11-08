package Buttons;

import com.badlogic.gdx.Gdx;

import Draws.ExitDraw;


public class ExitButton extends Button{

	public ExitButton(float X, float Y){
		this.draw = new ExitDraw(X, Y, 100, 300);
	}

	@Override
	public void Click() {
		Gdx.app.exit();
	}

}

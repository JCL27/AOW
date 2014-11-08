package Buttons;

import Draws.ExitDraw;

import com.badlogic.gdx.Gdx;


public class ExitButton extends Button{

	public ExitButton(float X, float Y){
		this.draw = new ExitDraw(X, Y, 100, 200);
	}

	@Override
	public void Click() {
		Gdx.app.exit();
	}

}

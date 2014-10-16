package Buttons;

import UserInterface.Clickable;
import ar.edu.itba.game.Element;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Texture;

public abstract class Button extends Element implements Clickable {

	public Button(Texture texture, float X, float Y, float velX, float velY,
			int scale, boolean gravity) {
		super(texture, X, Y, velX, velY, scale, gravity);
		// TODO Auto-generated constructor stub
	}
	
	public Button(Texture texture, float X, float Y, int scale){
		super(texture, X, Y, 0, 0, scale, false);
	}
	
	@Override
	public abstract void Click();

}

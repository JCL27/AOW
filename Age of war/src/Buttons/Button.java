package Buttons;

import Draws.Drawable;
import UserInterface.Clickable;
import ar.edu.itba.game.Element;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Texture;

public abstract class Button implements Clickable {

	protected Drawable draw;
	
	public boolean isClicked(double X, double Y){
		if(X> this.draw.getxPos() && Y>this.draw.getyPos() && X<(this.draw.getxPos()+this.draw.getScreenWidth())
				&& Y<((this.draw.getyPos()+this.draw.getScreenHeight()))){
			return true;
		}
		return false;
	}
	
	@Override
	public abstract void Click();
	
	public Drawable getDraw(){
		return this.draw;
	}
}

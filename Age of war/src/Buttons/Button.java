package Buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Draws.Drawable;
import UserInterface.Clickable;

public abstract class Button implements Clickable {

	protected Drawable draw;
	protected BitmapFont font;
	protected boolean messageVisible = false;	
	
	public Button(){
		this.font = new BitmapFont(false);
		font.setColor(Color.RED);
	}
	
	public boolean isClicked(float X, float Y){
		if(X> this.draw.getxPos() && Y>this.draw.getyPos() && X<(this.draw.getxPos()+this.draw.getScreenWidth())
				&& Y<((this.draw.getyPos()+this.draw.getScreenHeight()))){
			return true;
		}
		return false;
	}

	public void checkAndClick(){
		if (this.checkAvailability()==true){
			this.Click();
		}
	}

	public boolean checkAvailability(){
		return true;
	}
	
	public void setMessageVisibility(boolean visible){
		this.messageVisible = visible;
	}
	
	public boolean getMessageVisibility(){
		return this.messageVisible;
	}
	
	public void showMessage(SpriteBatch SB){}

	@Override
	public abstract void Click();

	public Drawable getDraw(){
		return this.draw;
	}
}

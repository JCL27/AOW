package Draws;

import ar.edu.itba.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BigGreenButtonDraw extends Drawable{
	
	static Texture leftTexture = Textures.GREEN_BUTTON;
	static int scale = 4;
	static int totalSprites = 1;
	
	private int screenHeight;
	private int screenWidth;
	private double xPos;
	private double yPos;
	
	public BigGreenButtonDraw(double xPos, double yPos) {
		super(xPos, yPos);
		this.xPos = xPos;
		this.yPos = yPos;
		this.screenHeight = leftTexture.getHeight() * (Game.HEIGHT * Game.SCALE)/Gdx.graphics.getHeight()/scale;
		this.screenWidth = leftTexture.getWidth()* (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth()/scale;
	}
	
	public int getScreenHeight(){
		return this.screenHeight;
	}
	public int getScreenWidth() {
		return this.screenWidth;
	}
	
	
	public Texture getTexture() {
		return leftTexture;
	}

}

package Draws;

import ar.edu.itba.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BasicProjectileDraw extends Drawable{
	static Texture leftTexture = Textures.GREEN_BUTTON;
	static int scale = 8;
	static int totalSprites = 1;
	
	private int screenHeight;
	private int screenWidth;
	private double xPos;
	private double yPos;
	
	public BasicProjectileDraw(double xPos, double yPos, int screenHeight, int screenWidth) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.xPos = xPos;
		this.yPos = yPos;
		//this.screenHeight = leftTexture.getHeight() * (Game.HEIGHT * Game.SCALE)/Gdx.graphics.getHeight()/scale;
		//this.screenWidth = leftTexture.getWidth()* (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth()/scale;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	
	}
	
	public int getScreenHeight(){
		return this.screenHeight;
	}
	public int getScreenWidth() {
		return this.screenWidth;
	}
	
	public int getSpriteWidth() {
		return leftTexture.getWidth();
	}

	public int getSpriteHeight() {
		return leftTexture.getHeight();
	}
	
	public Texture getTexture() {
		return leftTexture;
	}
}

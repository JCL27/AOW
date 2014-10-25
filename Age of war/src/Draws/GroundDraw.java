package Draws;

import ar.edu.itba.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GroundDraw extends Drawable{
	static Texture leftTexture = Textures.GROUND;
	static int scale = 1;
	static int totalSprites = 1;
	
	private int screenHeight;
	private int screenWidth;
	
	public GroundDraw(double xPos, double yPos) {
		super(xPos, yPos);
		this.screenHeight = Game.GROUND_HEIGHT * Gdx.graphics.getHeight() / (Game.HEIGHT * Game.SCALE);
		this.screenWidth = Gdx.graphics.getWidth();
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
	
	public int getSpriteWidth() {
		return leftTexture.getWidth();
	}

	public int getSpriteHeight() {
		return leftTexture.getHeight();
	}
}

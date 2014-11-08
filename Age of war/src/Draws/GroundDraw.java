package Draws;

import ar.edu.itba.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GroundDraw extends Drawable{
	private Texture leftTexture = Textures.GROUND;
	
	private int screenHeight;
	private int screenWidth;
	
	public GroundDraw(float xPos, float yPos) {
		super(xPos, yPos);
		this.screenHeight = Game.GROUND_HEIGHT;
		this.screenWidth = Gdx.graphics.getWidth() * Game.SCALE;
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

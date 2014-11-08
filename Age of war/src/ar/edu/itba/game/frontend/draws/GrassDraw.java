package Draws;

import com.badlogic.gdx.graphics.Texture;

public class GrassDraw extends Drawable {

	private Texture leftTexture = Textures.GRASS;
	
	private int screenHeight;
	private int screenWidth;
	
	public GrassDraw(float xPos, float yPos, int screenHeight, int screenWidth) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		//this.screenHeight = leftTexture.getHeight() * (Game.HEIGHT * Game.SCALE)/Gdx.graphics.getHeight()/scale;
		//this.screenWidth = leftTexture.getWidth()* (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth()/scale;
	
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

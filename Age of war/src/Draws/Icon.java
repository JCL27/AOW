package Draws;

import com.badlogic.gdx.graphics.Texture;

public class Icon extends Drawable {

Texture leftTexture;
	
	public Icon(float xPos2, float yPos2, Texture texture) {
		super(xPos2, yPos2, 50, 50);
		this.screenHeight = 50;
		this.screenWidth = 50;
		
		this.leftTexture = texture;
	}

	@Override
	public int getScreenWidth() {
		return this.screenWidth;
	}

	@Override
	public int getScreenHeight() {
		return this.screenHeight;
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

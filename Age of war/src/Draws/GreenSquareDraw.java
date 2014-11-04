package Draws;

import com.badlogic.gdx.graphics.Texture;

public class GreenSquareDraw extends Drawable {

	private Texture leftTexture = Textures.GREEN_SQUARE;
	
	public GreenSquareDraw(float xPos2, float yPos2, int screenHeight, int screenWidth) {
		super(xPos2, yPos2, screenHeight, screenWidth);
	}

	@Override
	public int getScreenWidth() {
		return this.screenWidth;
	}

	@Override
	public int getScreenHeight() {
		return this.screenHeight;
	}
	
	public void setScreenHeight(int newHeight){
		this.screenHeight = newHeight;
	}

	public void setScreenWidth(int newWidth){
		this.screenWidth = newWidth;
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

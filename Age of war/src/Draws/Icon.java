package Draws;

import com.badlogic.gdx.graphics.Texture;

public class Icon extends Drawable {

	
	public Icon(float xPos2, float yPos2, Texture texture) {
		super(xPos2, yPos2, 50, 50);
		this.screenHeight = 50;
		this.screenWidth = 50;
		
		this.leftTexture = texture;
	}

	public Icon(float xPos2, float yPos2, int screenHeight, int screenWidth, Texture enabledTexture, Texture disabledTexture) {
		super(xPos2, yPos2, screenHeight, screenWidth, enabledTexture, disabledTexture);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.leftTexture = enabledTexture;
		this.enabledTexture = enabledTexture;
		this.disabledTexture = disabledTexture;
		
	}
	
	public Icon(float xPos2, float yPos2, int screenHeight, int screenWidth, Texture texture) {
		super(xPos2, yPos2, screenHeight, screenWidth);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.leftTexture = texture;
		this.enabledTexture = texture;
		this.disabledTexture = texture;
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

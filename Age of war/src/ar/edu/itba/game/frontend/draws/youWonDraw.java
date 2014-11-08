package Draws;

public class youWonDraw extends Drawable {

	public youWonDraw(float xPos, float yPos, int screenHeight,
			int screenWidth) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.leftTexture = Textures.YOU_WON;
	}

	@Override
	public int getScreenWidth() {
		return this.screenWidth;
	}

	@Override
	public int getScreenHeight() {
		return this.screenHeight;
	}

}

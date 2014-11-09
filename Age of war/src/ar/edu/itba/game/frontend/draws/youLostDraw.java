package ar.edu.itba.game.frontend.draws;

public class youLostDraw extends Drawable {

	public youLostDraw(float xPos, float yPos, int screenHeight,
			int screenWidth) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.leftTexture = Textures.YOU_LOST;
	}

	@Override
	public int getScreenWidth() {
		return screenWidth;
	}

	@Override
	public int getScreenHeight() {
		return screenHeight;
	}

}

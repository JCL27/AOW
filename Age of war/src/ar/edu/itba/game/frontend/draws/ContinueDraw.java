package Draws;

public class ContinueDraw extends Drawable {

	public ContinueDraw(float xPos2, float yPos2, int screenHeight,
			int screenWidth) {
		super(xPos2, yPos2, screenHeight, screenWidth);
		this.leftTexture = Textures.CONTINUE_BUTTON;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}

	@Override
	public int getScreenWidth() {
		// TODO Auto-generated method stub
		return this.screenWidth;
	}

	@Override
	public int getScreenHeight() {
		// TODO Auto-generated method stub
		return this.screenHeight;
	}

}

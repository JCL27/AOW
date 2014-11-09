package ar.edu.itba.game.frontend.draws;


public class LoadDraw extends Drawable{

	public LoadDraw(float xPos, float yPos, int screenHeight, int screenWidth) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.leftTexture = Textures.LOAD_BUTTON;
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

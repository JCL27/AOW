package ar.edu.itba.game.frontend.draws;


public class SaveDraw extends Drawable{
	
	
	
	public SaveDraw(float xPos, float yPos, int screenHeight, int screenWidth) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.leftTexture = Textures.SAVE_BUTTON;
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

package ar.edu.itba.game.frontend.draws;


public class ExitDraw extends Drawable {

	public ExitDraw(float xPos, float yPos, int screenHeight, int screenWidth){
		super(xPos, yPos, screenHeight, screenWidth);
		this.leftTexture = Textures.EXIT_BUTTON;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
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

package ar.edu.itba.game.frontend.draws;


public class NewGameDraw extends Drawable {

	public NewGameDraw(float xPos, float yPos, int screenHeight, int screenWidth) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.leftTexture = Textures.NEW_GAME_BUTTON;
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

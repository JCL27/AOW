package ar.edu.itba.game.frontend.draws;

import com.badlogic.gdx.graphics.Texture;

public class MainButtonDraw extends Drawable {

	private int scale = 2;
	
	private int screenHeight;
	private int screenWidth;
	
	
	public MainButtonDraw(float xPos, float yPos, int screenHeight, int screenWidth) {
		super(xPos, yPos);
		this.leftTexture = Textures.MAIN_BUTTON;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	@Override
	public int getScreenWidth() {
		return this.screenWidth;
	}

	@Override
	public int getScreenHeight() {
		// TODO Auto-generated method stub
		return this.screenHeight;
	}
	
	public Texture getTexture() {
		return leftTexture;
	}


}

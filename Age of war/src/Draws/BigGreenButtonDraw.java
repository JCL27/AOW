package Draws;

import ar.edu.itba.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BigGreenButtonDraw extends Drawable{
	
	private Texture leftTexture = Textures.GREEN_BUTTON;
	private int scale = 2;
	private int totalSprites = 1;
	
	private int screenHeight;
	private int screenWidth;
	
	public BigGreenButtonDraw(float xPos, float yPos) {
		super(xPos, yPos);

		this.screenHeight = leftTexture.getHeight() * (Game.HEIGHT * Game.SCALE)/Gdx.graphics.getHeight()/scale;
		this.screenWidth = leftTexture.getWidth()* (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth()/scale;
	}
	
	public int getScreenHeight(){
		return this.screenHeight;
	}
	public int getScreenWidth() {
		return this.screenWidth;
	}
	
	
	public Texture getTexture() {
		return leftTexture;
	}

}

package Draws;

import ar.edu.itba.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ArrowDraw extends Drawable {

	private Texture leftTexture = Textures.ARROW;
	private int scale = 1;
	private int totalSprites = 1;
	
	private int screenHeight;
	private int screenWidth;
	
	public ArrowDraw(float xPos, float yPos) {
		super(xPos, yPos);

		this.screenHeight = leftTexture.getHeight() * (Game.HEIGHT * Game.SCALE)/Gdx.graphics.getHeight()/scale;
		this.screenWidth = leftTexture.getWidth()* (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth()/scale;
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

	
	public Texture getTexture() {
		return leftTexture;
	}
}

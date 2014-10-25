package Draws;

import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Texture;

public class TowerDraw extends Drawable{
	static Texture leftTexture;
	static Texture rightTexture;
	static int scale = 4;
	static int totalSprites = 1;
	public TowerDraw(float xPos, float yPos, int screenHeight, int screenWidth, Player player) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.player = player;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
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

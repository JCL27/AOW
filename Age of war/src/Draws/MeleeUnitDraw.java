package Draws;

import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Texture;

public class MeleeUnitDraw extends Drawable{
	
	static Texture leftTexture = Textures.BLUE_BALL;
	static Texture rightTexture = Textures.RED_BALL;
	static int scale = 4;
	static int totalSprites = 1;
	
	public MeleeUnitDraw(float xPos, float yPos, int screenHeight, int screenWidth, Player player) {
			super(xPos, yPos, screenHeight, screenWidth);
			this.player = player;
			//this.screenHeight = leftTexture.getHeight()/scale;
			//this.screenWidth = leftTexture.getWidth()/scale;
			this.screenHeight = screenHeight;
			this.screenWidth = screenWidth;
	}
	
	public int getScreenHeight(){
		return this.screenHeight;
	}
	
	public int getScreenWidth(){
		return this.screenWidth;
	}
	
	public Texture getTexture(){
		if(this.player.equals(WorldManager.getInstance().getPlayer())){
			return leftTexture;
		}else{
			return rightTexture;
		}	
	}
	
	public int getSpriteWidth() {
		return this.getTexture().getWidth();
	}

	public int getSpriteHeight() {
		return this.getTexture().getHeight();
	}
}

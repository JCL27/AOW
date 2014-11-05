package Draws;

import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Texture;

public class FlyingUnitDraw extends Drawable {
	private Texture leftTexture = Textures.AIR_UNIT;
	private Texture rightTexture = Textures.AIR_UNIT;

	
	public FlyingUnitDraw(float xPos, float yPos, int screenHeight, int screenWidth, Player player) {
			super(xPos, yPos, screenHeight, screenWidth);
			this.player = player;
			//this.screenHeight = leftTexture.getHeight()/scale;
			//this.screenWidth = leftTexture.getWidth()/scale;
			//this.screenHeight = screenHeight;
			//this.screenWidth = screenWidth;
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

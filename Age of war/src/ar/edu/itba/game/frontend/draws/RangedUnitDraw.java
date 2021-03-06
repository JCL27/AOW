package ar.edu.itba.game.frontend.draws;

import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;

import com.badlogic.gdx.graphics.Texture;

public class RangedUnitDraw extends Drawable{
	
	private Texture leftTexture = Textures.BLUE_BALL;
	private Texture rightTexture = Textures.RED_BALL;
	
	public RangedUnitDraw(float xPos, float yPos, int screenHeight, int screenWidth, Player player) {
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

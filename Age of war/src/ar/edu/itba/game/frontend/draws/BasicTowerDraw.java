package Draws;

import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Texture;

public class BasicTowerDraw extends Drawable{
	
	private Texture leftTexture = Textures.BASIC_TOWER;
	private Texture rightTexture = Textures.BASIC_TOWER;
	
	public BasicTowerDraw(float xPos, float yPos, int screenHeight, int screenWidth, Player player) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.player = player;
		//this.screenHeight = leftTexture.getHeight()/scale;
		//this.screenWidth = leftTexture.getWidth()/scale;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
}
	
	public Texture getTexture(){
		if(this.player.equals(WorldManager.getInstance().getPlayer())){
			return leftTexture;
		}else{
			return rightTexture;
		}	
	}
	public int getSpriteWidth() {
		return leftTexture.getWidth();
	}
	
	public int getSpriteHeight() {
		return leftTexture.getHeight();
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

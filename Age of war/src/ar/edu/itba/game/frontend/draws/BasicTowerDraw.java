package ar.edu.itba.game.frontend.draws;

import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.frontend.draws.Textures;
import com.badlogic.gdx.graphics.Texture;



public class BasicTowerDraw extends Drawable{
	
	private Texture leftTexture = Textures.TOWER_BLUE;
	private Texture rightTexture = Textures.TOWER_RED;
	
	public BasicTowerDraw(float xPos, float yPos, int screenHeight, int screenWidth, Player player) {
		super(xPos, yPos, screenHeight, screenWidth);
		this.player = player;
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

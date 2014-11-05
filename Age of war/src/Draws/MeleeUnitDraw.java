package Draws;

import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Texture;

public class MeleeUnitDraw extends Drawable {
	
	//DRAWABLE: BORRAR EL CONSTRUCTOR CON 2 PARAM, QUE RECIBA PLAYER
	//BORRAR lefttexture y rightTexture, scale totalSprites de Drawable
	private Texture leftTexture = Textures.BLUE_BALL;
	private Texture rightTexture = Textures.RED_BALL;
	
	public MeleeUnitDraw(float xPos, float yPos, int screenHeight, int screenWidth, Player player){
		super(xPos, yPos, screenHeight, screenWidth);
		this.player = player;
	}
	
	@Override
	public int getScreenWidth() {
		return this.screenWidth;
	}

	@Override
	public int getScreenHeight() {
		return this.screenHeight;
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

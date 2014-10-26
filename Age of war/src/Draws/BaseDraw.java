package Draws;

import ar.edu.itba.game.Side;

import com.badlogic.gdx.graphics.Texture;

public class BaseDraw extends Drawable {
	static Texture leftTexture = Textures.BLUE_BASE;
	static Texture rightTexture = Textures.RED_BASE;
	static int scale = 4;
	static int totalSprites = 1;
	private Side side;
	
	public BaseDraw(float xPos, float yPos, int screenHeight, int screenWidth, Side side) {
			super(xPos, yPos, screenHeight, screenWidth);
			this.side = side;
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
		if(this.side == Side.LEFT){
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

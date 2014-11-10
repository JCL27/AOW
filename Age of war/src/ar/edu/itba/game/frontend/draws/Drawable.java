package ar.edu.itba.game.frontend.draws;

import ar.edu.itba.game.backend.logic.Player;

import com.badlogic.gdx.graphics.Texture;

/**
 * Basically, drawable is a class that saves one or more Textures and sets methods to manage the textures
 * more practically
 * 
 *
 */
public abstract class Drawable {
	protected static int scale;
	protected Texture leftTexture;
	protected Texture rightTexture;
	protected Texture disabledTexture;
	protected Texture enabledTexture;
	
	protected float xPos;
	protected float yPos;
	protected Player player;
	
	protected int screenWidth;
	protected int screenHeight;

	public Drawable(float xPos, float yPos, int screenHeight, int screenWidth,
			Texture enabledTexture, Texture disabledTexture){
		this.xPos = xPos;
		this.yPos = yPos;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enabledTexture = enabledTexture;
		this.disabledTexture = disabledTexture;
		this.leftTexture = enabledTexture;
	}
	
	public Drawable(float xPos2, float yPos2, int screenHeight, int screenWidth){
		this.xPos = xPos2;
		this.yPos = yPos2;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	public Drawable(float xPos2, float yPos2){
		this.xPos = xPos2;
		this.yPos = yPos2;
	}

	public void setEnabled(){
		this.leftTexture = this.enabledTexture;
	}
	
	public void setDisabled(){
		this.leftTexture = this.disabledTexture;
	}
	
	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public int getScale() {
		return scale;
	}

	public Texture getTexture() {
		return leftTexture;
	}
	
	public abstract int getScreenWidth();

	public abstract int getScreenHeight();

	public int getSpriteWidth() {
		return leftTexture.getWidth();
	}
	
	public int getSpriteHeight() {
		return leftTexture.getHeight();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + screenHeight;
		result = prime * result + screenWidth;
		long temp;
		temp = Double.doubleToLongBits(xPos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yPos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Drawable other = (Drawable) obj;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (screenHeight != other.screenHeight)
			return false;
		if (screenWidth != other.screenWidth)
			return false;
		if (Double.doubleToLongBits(xPos) != Double
				.doubleToLongBits(other.xPos))
			return false;
		if (Double.doubleToLongBits(yPos) != Double
				.doubleToLongBits(other.yPos))
			return false;
		return true;
	}
	
	
}

package Draws;

import ar.edu.itba.game.Player;

import com.badlogic.gdx.graphics.Texture;

public abstract class Drawable {
	protected static int scale;
	protected static Texture leftTexture;
	protected static Texture rightTexture;
	protected static int totalSprites;
	
	protected float xPos;
	protected float yPos;
	protected Player player;
	
	protected int screenWidth;
	protected int screenHeight;
	protected int currentSprite;
	
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

	public double getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public int getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(int currentSprite) {
		this.currentSprite = currentSprite;
	}

	public int getScale() {
		return scale;
	}

	public Texture getTexture() {
		return leftTexture;
	}

	public abstract int getScreenWidth();

	public abstract int getScreenHeight();

	public int getTotalSprites() {
		return totalSprites;
	}

	public int getSpriteWidth() {
		return leftTexture.getWidth();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentSprite;
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
		if (currentSprite != other.currentSprite)
			return false;
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

	public int getSpriteHeight() {
		return leftTexture.getHeight();
	}
	
	
	
}

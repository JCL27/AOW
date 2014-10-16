package ar.edu.itba.game;

import com.badlogic.gdx.graphics.Texture;

public class Element {
	private Texture texture;
	private double X;
	private double Y;
	private double velX;
	private double velY;
	private int scale;
	private boolean gravity;
	
	public Element(Texture texture, float X, float Y, float velX, float velY, int scale, boolean gravity){
		this.texture = texture;
		this.X = X;
		this.Y = Y;
		this.velX = velX;
		this.velY = velY;
		this.gravity = gravity;
		this.scale = scale;
	}

	public boolean isContained(double X, double Y){
		if((this.X < X) && ((this.X + this.getWidth() / this.scale) > X) && (this.Y < Y) && (this.Y + this.getHeight()/this.scale)>Y){
			return true;
		}
		return false;	
	}
	
	public int getScale(){
		return this.scale;
	}
	
	public int getHeight(){
		return this.texture.getHeight();
	}
	
	public int getScreenHeight(){
		return this.getHeight()/this.scale;
	}
	
	public int getWidth(){
		return this.texture.getWidth();
	}
	
	public int getScreenWidth(){
		return this.getWidth()/(this.scale);
	}
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public boolean isGravity() {
		return gravity;
	}

	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}
	
	public double getMiddleX(){
		return this.getX() + (this.getWidth()/(2*this.scale));
	}
	
	public double getMiddleY(){
		return this.getY() + (this.getHeight()/(2*this.scale));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(X);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (gravity ? 1231 : 1237);
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
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
		Element other = (Element) obj;
		if (Double.doubleToLongBits(X) != Double.doubleToLongBits(other.X))
			return false;
		if (Double.doubleToLongBits(Y) != Double.doubleToLongBits(other.Y))
			return false;
		if (gravity != other.gravity)
			return false;
		if (texture == null) {
			if (other.texture != null)
				return false;
		} else if (!texture.equals(other.texture))
			return false;
		return true;
	}

	
}

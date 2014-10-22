package ar.edu.itba.game;

import java.awt.geom.Point2D;

import com.badlogic.gdx.graphics.Texture;

public class Element {

	private double X;
	private double Y;
	private double velX;
	private double velY;
	private boolean gravity;
	private int Height;
	private int Width;
	
	
	public Element(float X, float Y, float velX, float velY, int Height, int Width ,boolean gravity){
		this.X = X;
		this.Y = Y;
		this.Height = Height;
		this.Width = Width;
		this.velX = velX;
		this.velY = velY;
		this.gravity = gravity;
	}
	
	public Element(float X, float Y, int Height, int Width){
		this.X = X;
		this.Y = Y;
		this.Height = Height;
		this.Width = Width;
		this.velX = 0;
		this.velY = 0;
		this.gravity = false;
	}

	public boolean isContained(double X, double Y){
		if((this.X < X) && ((this.X + this.getWidth() > X) && (this.Y < Y) && (this.Y + this.getHeight())>Y)){
			return true;
		}
		return false;	
	}
	
	public boolean isContained(Point2D.Double point){
		if((this.X < point.x) && ((this.X + this.getWidth() > point.x) &&
				(this.Y < point.y) && (this.Y + this.getHeight())>point.y)){
			return true;
		}
		return false;	
	}

	public int getHeight(){
		return this.Height;
	}
	
	
	public int getWidth(){
		return this.Width;
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
		return this.getX() + (this.getWidth()/2);
	}
	
	public double getMiddleY(){
		return this.getY() + (this.getHeight()/2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Height;
		result = prime * result + Width;
		long temp;
		temp = Double.doubleToLongBits(X);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (gravity ? 1231 : 1237);
		temp = Double.doubleToLongBits(velX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(velY);
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
		Element other = (Element) obj;
		if (Height != other.Height)
			return false;
		if (Width != other.Width)
			return false;
		if (Double.doubleToLongBits(X) != Double.doubleToLongBits(other.X))
			return false;
		if (Double.doubleToLongBits(Y) != Double.doubleToLongBits(other.Y))
			return false;
		if (gravity != other.gravity)
			return false;
		if (Double.doubleToLongBits(velX) != Double
				.doubleToLongBits(other.velX))
			return false;
		if (Double.doubleToLongBits(velY) != Double
				.doubleToLongBits(other.velY))
			return false;
		return true;
	}
	
	

	
}

package ar.edu.itba.game;

import java.awt.geom.Point2D;

public class Projectile {
	private Element element;
	private int damage;
	
	public double getMiddleX(){
		return this.element.getMiddleX();
	}
	
	public double getMiddleY(){
		return this.element.getMiddleY();
	}

	public int getDamage(){
		return this.damage;
	}
	
	public Element getElement(){
		return this.element;
	}
	
	public Point2D.Double getCollisionPoint(){
		return new Point2D.Double(this.getMiddleX(), this.element.getY() + this.element.getHeight()/this.element.getScale());
	}
	
	public Projectile(double X, double Y, double velX, double velY, boolean gravity, int damage){
		this.element = new Element(Textures.GREEN_BUTTON , (float)(X-Textures.GREEN_BUTTON.getWidth()/24)  , (float)Y, (float)velX, (float)velY, 12 , true);
		this.damage = damage;
		WorldManager.getInstance().getElements().add(this.element);
		
	}

}

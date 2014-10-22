package ar.edu.itba.game;

import java.awt.geom.Point2D;
import java.util.Observable;

import Observers.ProjectileObserver;
import UserInterface.Textures;

public class Projectile extends Observable{
	private Element element;
	private int damage;
	private ProjectileObserver observer;
	
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
		return new Point2D.Double(this.getMiddleX(), this.element.getY() + this.element.getHeight());
	}
	
	public Projectile(double X, double Y, double velX, double velY, boolean gravity, int damage){
		this.element = new Element((float)(X - 15) , (float)Y, (float)velX, (float)velY, 30, 50, true);
		this.damage = damage;
		WorldManager.getInstance().getElements().add(this.element);
		this.observer = new ProjectileObserver(this);
		this.addObserver(this.observer);
	}
	
	public void notifyObservers(){
		this.observer.update(null, null);
	}

	public void notifyDelete() {
		this.observer.dispose();	
	}

}

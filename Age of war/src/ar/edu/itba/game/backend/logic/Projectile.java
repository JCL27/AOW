package ar.edu.itba.game.backend.logic;

import java.awt.geom.Point2D;
import java.io.Serializable;

import ar.edu.itba.game.frontend.observers.ProjectileObserver;

public class Projectile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3388226133504707762L;
	private Element element;
	private int damage;
	private transient ProjectileObserver observer;
	
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
	
	public Projectile(float X, float Y, float velX, float velY, boolean gravity, int damage, ProjectileObserver observer){
		this.element = new Element((X - 15) , Y, velX, velY, 20, 20, true);
		this.damage = damage;
		WorldManager.getInstance().getElements().add(this.element);
		this.observer = observer;
	}
	
	public void notifyObservers(){
		this.observer.update(this);
	}

	public void notifyDelete() {
		this.observer.dispose(this);	
	}

	public void setObserver(ProjectileObserver observer) {
		this.observer = observer;
	}
}

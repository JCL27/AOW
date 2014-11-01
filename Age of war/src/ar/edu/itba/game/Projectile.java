package ar.edu.itba.game;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Observable;

import Observers.ProjectileObserver;
import Observers.UnitObserver;

public class Projectile extends Observable implements Serializable{
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
	
	public Projectile(float X, float Y, float velX, float velY, boolean gravity, int damage){
		this.element = new Element((X - 15) , Y, velX, velY, 20, 20, true);
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
	
	public void reAssignObserver(){
		this.deleteObservers();
		this.observer = new ProjectileObserver(this);
		this.addObserver(this.observer);
		
	}
}

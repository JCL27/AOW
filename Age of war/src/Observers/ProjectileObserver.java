package Observers;

import java.util.Observable;
import java.util.Observer;

import Draws.BasicProjectileDraw;
import Draws.Drawable;
import UserInterface.UIManager;
import ar.edu.itba.game.Projectile;

public class ProjectileObserver implements Observer{
	private Projectile pjt;
	private Drawable draw;

	
	public ProjectileObserver(Projectile pjt){
		this.pjt = pjt;
		this.draw = new BasicProjectileDraw(pjt.getElement().getX(), pjt.getElement().getY(), pjt.getElement().getHeight(), pjt.getElement().getWidth());
		UIManager.getInstance().getDraws().add(this.draw);
	}
	
	public void update(Observable arg0, Object arg1) {
		this.draw.setxPos(pjt.getElement().getX());
		this.draw.setyPos(pjt.getElement().getY());
		
	}

	public void dispose() {
		UIManager.getInstance().getDraws().remove(this.draw);
	}
	
	
}

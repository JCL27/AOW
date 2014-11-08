package Observers;

import Draws.BasicProjectileDraw;
import Draws.Drawable;
import UserInterface.UIManager;
import ar.edu.itba.game.Projectile;

public class ProjectileObserver{

	public ProjectileObserver(){
	}
	
	public void addProjectile(Projectile pjt){
		Drawable draw = new BasicProjectileDraw(pjt.getElement().getX(), pjt.getElement().getY(),
				pjt.getElement().getHeight(), pjt.getElement().getWidth());
		UIManager.getInstance().getProjectilesDraws().put(pjt, draw);
	}
	
	public void update(Projectile pjt) {
		UIManager.getInstance().getProjectilesDraws().get(pjt).setxPos(pjt.getElement().getX());
		UIManager.getInstance().getProjectilesDraws().get(pjt).setyPos(pjt.getElement().getY());
	}

	public void dispose(Projectile pjt) {
		UIManager.getInstance().getProjectilesDraws().remove(pjt);
	}
}

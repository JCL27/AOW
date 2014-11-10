package ar.edu.itba.game.frontend.observers;

import ar.edu.itba.game.backend.logic.Projectile;
import ar.edu.itba.game.frontend.draws.BasicProjectileDraw;
import ar.edu.itba.game.frontend.draws.Drawable;
import ar.edu.itba.game.frontend.userinterface.UIManager;

/**
 * 
 * Tracks the position of the projectiles to draw them to screen
 *
 */
public class ProjectileObserver{

	public ProjectileObserver(){
	}
	
	/**
	 * Add the current projectile and its draw(with its position) to the UIManagers hashmap, so that it can be drawn
	 * @param pjt
	 */
	public void addProjectile(Projectile pjt){
		Drawable draw = new BasicProjectileDraw(pjt.getElement().getX(), pjt.getElement().getY(),
				pjt.getElement().getHeight(), pjt.getElement().getWidth());
		UIManager.getInstance().getProjectilesDraws().put(pjt, draw);
	}
	
	/**
	 * Updates the current draw position with the current projectile position
	 * @param pjt
	 */
	public void update(Projectile pjt) {
		UIManager.getInstance().getProjectilesDraws().get(pjt).setxPos(pjt.getElement().getX());
		UIManager.getInstance().getProjectilesDraws().get(pjt).setyPos(pjt.getElement().getY());
	}
	
	/**
	 * Removes the current projectile from the hasmap so that its not going to be drawn
	 * @param pjt
	 */
	public void dispose(Projectile pjt) {
		UIManager.getInstance().getProjectilesDraws().remove(pjt);
	}
}

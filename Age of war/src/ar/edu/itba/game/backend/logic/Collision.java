package ar.edu.itba.game.backend.logic;

/**
 * Collision between a projectile and an attackable objective
 */
public class Collision {
	Projectile pjt;
	Attackable objective;

	public Collision(Projectile pjt, Attackable objective){
		this.pjt = pjt;
		this.objective = objective;
	}
	
	public void Collide(){
		this.objective.receiveDamage(pjt.getDamage());
		WorldManager.getInstance().disposeProjectile(this.pjt);
	}
}


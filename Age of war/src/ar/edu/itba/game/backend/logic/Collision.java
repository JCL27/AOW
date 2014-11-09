package ar.edu.itba.game.backend.logic;


public class Collision {
	Projectile pjt;
	Attackable victim;

	public Collision(Projectile pjt, Attackable victim){
		this.pjt = pjt;
		this.victim = victim;
	}

	public void Collide(){
		this.victim.receiveDamage(pjt.getDamage());
		WorldManager.getInstance().disposeProjectile(this.pjt);
	}
}


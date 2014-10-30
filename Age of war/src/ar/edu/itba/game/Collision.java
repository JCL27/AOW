package ar.edu.itba.game;

import exceptions.DeadUnitException;

public class Collision {
	Projectile pjt;
	Attackable victim;
	
	public Collision(Projectile pjt, Attackable victim){
		this.pjt = pjt;
		this.victim = victim;
	}
	
	public void Collide(){
		try {
			this.victim.receiveDamage(pjt.getDamage());
		} catch (DeadUnitException e) {
			WorldManager.getInstance().killUnit(e.getUnit());
		} finally{
			WorldManager.getInstance().disposeProjectile(this.pjt);
		}
	}
}

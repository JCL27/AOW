package ar.edu.itba.game;

import exceptions.DeadUnitException;

public class Collision {
	Projectile pjt;
	Unit unit;
	
	public Collision(Projectile pjt, Unit unit){
		this.pjt = pjt;
		this.unit = unit;
	}
	
	public void Collide(){
		try {
			this.unit.receiveDamage(pjt.getDamage());
		} catch (DeadUnitException e) {
			WorldManager.getInstance().killUnit(this.unit);
		} finally{
			WorldManager.getInstance().disposeProjectile(this.pjt);
		}
	}
}

package ar.edu.itba.game;

public interface CanAttack {
	public void attack(Attackable objective);
	public int getAttackRange();
	public Side getSide();
	public float getX();
	public int getWidth();
	public boolean canAttackFlying();
	public static int fff() {
		return 0;
	}
}

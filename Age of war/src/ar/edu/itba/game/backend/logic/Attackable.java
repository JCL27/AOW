package ar.edu.itba.game.backend.logic;


public interface Attackable {
	public void receiveDamage(int damage);
	public Element getElement();
	public boolean doesFly();
}

package ar.edu.itba.game;


public interface Attackable {
	public void receiveDamage(int damage);
	public Element getElement();
	public boolean doesFly();
}

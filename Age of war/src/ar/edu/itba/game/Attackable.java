package ar.edu.itba.game;

import exceptions.DeadUnitException;

public interface Attackable {
	public void receiveDamage(int damage);
	public Element getElement();
	public boolean doesFly();
}

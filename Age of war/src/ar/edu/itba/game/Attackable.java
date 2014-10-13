package ar.edu.itba.game;

import exceptions.DeadUnitException;
//ALTO COMMENT
public interface Attackable {
	public void receiveDamage(int damage) throws DeadUnitException;
	public Element getElement();
}

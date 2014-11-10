package ar.edu.itba.game.frontend.observers;

import ar.edu.itba.game.backend.units.Unit;
import ar.edu.itba.game.frontend.drawableobjects.UnitDraw;
import ar.edu.itba.game.frontend.userinterface.UIManager;
/**
 * Draws the unit and its bar, and updates the position and the HP of the visual representation
 * 
 *
 */
public class UnitObserver{
	
	/**
	 * Adds a unit to the units hashmap at UIManager with its visual representation and the HP bar
	 * @param unit
	 */
	public void addUnit(Unit unit){
		UnitDraw uDraw = new UnitDraw(unit.getX(), unit.getY(), unit);
		UIManager.getInstance().getUnitsDraws().put(unit, uDraw);
	}
	
	/**
	 * Updates the draws to the current HP and position
	 * @param unit
	 */
	public void update(Unit unit) {
		UnitDraw uDraw = UIManager.getInstance().getUnitsDraws().get(unit);
		uDraw.setCurrent(unit.getHp(), unit.getX(), unit.getY());
	}
	
	/**
	 * Removes from the UIManager units hashmap when it dies
	 * @param unit
	 */
	public void dispose(Unit unit) {
		UIManager.getInstance().getUnitsDraws().remove(unit);
	}
	
	
}

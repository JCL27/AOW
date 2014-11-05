package Observers;

import DrawableObjects.UnitDraw;
import Units.Unit;
import UserInterface.UIManager;

public class UnitObserver{
	
	public void addUnit(Unit unit){
		UnitDraw uDraw = new UnitDraw(unit.getX(), unit.getY(), unit);
		UIManager.getInstance().getUnitsDraws().put(unit, uDraw);
	}
	
	public void update(Unit unit) {
		UnitDraw uDraw = UIManager.getInstance().getUnitsDraws().get(unit);
		uDraw.setCurrent(unit.getHp(), unit.getX(), unit.getY());
	}
	
	public void dispose(Unit unit) {
		UIManager.getInstance().getUnitsDraws().remove(unit);
	}
	
	
}

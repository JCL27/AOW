package Observers;

import java.util.Observable;
import java.util.Observer;

import Draws.Drawable;
import Draws.FlyingUnitDraw;
import Draws.MeleeUnitDraw;
import UserInterface.UIManager;
import ar.edu.itba.game.Unit;

public class UnitObserver implements Observer{
	private Unit unit;

	private Drawable draw;
	
	public UnitObserver(Unit unit){
		this.unit = unit;
		switch(unit.getClass().getSimpleName()){
			case("MeleeUnit"):
				this.draw = new MeleeUnitDraw(unit.getX(), unit.getY(), (int)unit.getHeight(), (int)unit.getWidth(), unit.getPlayer());
				break;
			case("FlyingUnit"):
				this.draw = new FlyingUnitDraw(unit.getX(), unit.getY(), (int)unit.getHeight(), (int)unit.getWidth(), unit.getPlayer());
		}
		UserInterface.UIManager.getInstance().getDraws().add(this.draw);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.draw.setxPos(unit.getX());
		this.draw.setyPos(unit.getY());
		
	}
	
	public void dispose() {
		UIManager.getInstance().getDraws().remove(this.draw);
	}
	
	
}

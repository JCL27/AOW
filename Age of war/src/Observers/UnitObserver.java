package Observers;

import java.util.Observable;
import java.util.Observer;

import javax.swing.UIManager;

import Draws.Drawable;
import Draws.MeleeUnitDraw;
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
		}
		UserInterface.UIManager.getInstance().getDraws().add(this.draw);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.draw.setxPos(unit.getX());
		this.draw.setyPos(unit.getY());
		
	}
	
	
	
}

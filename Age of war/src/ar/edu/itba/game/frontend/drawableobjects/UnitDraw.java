package ar.edu.itba.game.frontend.drawableobjects;

import java.util.ArrayList;

import ar.edu.itba.game.backend.units.Unit;
import ar.edu.itba.game.frontend.draws.Drawable;
import ar.edu.itba.game.frontend.draws.FlyingUnitDraw;
import ar.edu.itba.game.frontend.draws.MeleeUnitDraw;
import ar.edu.itba.game.frontend.draws.RangedUnitDraw;
/**
 * Represents a Unit and it's HP Bar
 * @author JuanCruz
 *
 */
public class UnitDraw implements DrawableObject {
	private Drawable draw;
	private Bar bar;
	private ArrayList<Drawable> draws = new ArrayList<Drawable>();
	
	/**
	 * Assigns an image to the unit's draw and sets the Bar
	 * @param xPos
	 * @param yPos
	 * @param unit
	 */
	public UnitDraw(float xPos, float yPos, Unit unit){
		switch(unit.getClass().getSimpleName()){
		case("MeleeUnit"):
			this.draw = new MeleeUnitDraw(unit.getX(), unit.getY(), (int)unit.getHeight(), (int)unit.getWidth(), unit.getPlayer());
			break;
		case("RangedUnit"):
			this.draw = new RangedUnitDraw(unit.getX(), unit.getY(), (int)unit.getHeight(), (int)unit.getWidth(), unit.getPlayer());
			break;
		case("AntiaircraftUnit"):
			this.draw = new RangedUnitDraw(unit.getX(), unit.getY(), (int)unit.getHeight(), (int)unit.getWidth(), unit.getPlayer());	
			break;
		case("FlyingUnit"):
			this.draw = new FlyingUnitDraw(unit.getX(), unit.getY(), (int)unit.getHeight(), (int)unit.getWidth(), unit.getPlayer());	
			break;
		}
		bar = new Bar(unit.getMaxHp(), unit.getMaxHp(), 20, unit.getWidth(), (int)unit.getX(), (int) unit.getY() + unit.getHeight());
		draws.add(this.draw);
		draws.addAll(this.bar.getDraws());
	}
	
	@Override
	public ArrayList<Drawable> getDraws() {
		return this.draws;
	}

	@Override
	public void setCurrent(int current, float x, float y) {
		this.draw.setxPos(x);
		this.draw.setyPos(y);
		this.bar.setCurrent(current, x, y);
	}

	@Override
	public void setCurrent(int current) {
		this.bar.setCurrent(current);
	}

}

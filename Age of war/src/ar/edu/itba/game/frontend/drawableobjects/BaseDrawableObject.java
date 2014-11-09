package ar.edu.itba.game.frontend.drawableobjects;

import java.util.ArrayList;

import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.frontend.draws.BaseDraw;
import ar.edu.itba.game.frontend.draws.Drawable;
/**
 * Consists of a Bar that indicates de base's HP and a common drawable that just shows the collision box of the base
 * 
 *
 */
public class BaseDrawableObject implements DrawableObject {

	private Bar bar;
	private BaseDraw draw;
	private ArrayList<Drawable> draws = new ArrayList<Drawable>();
	
	public BaseDrawableObject(float x, float y, int height, int width, Side side, int maxHp) {
		draw = new BaseDraw(x, y, height, width, side);
		bar = new Bar(maxHp, maxHp, 30, width - 30, (int)x + 15, (int)y + height);
		this.draws.addAll(bar.getDraws());
		this.draws.add(this.draw);
	}

	@Override
	public ArrayList<Drawable> getDraws() {
		return draws;
	}
	/**
	 * Updates the Bar to the current value of HP of the base
	 */
	@Override
	public void setCurrent(int current, float x, float y) {
		this.bar.setCurrent(current, x, y);
	}

	@Override
	public void setCurrent(int current) {
		this.bar.setCurrent(current);
	}

}

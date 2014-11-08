package DrawableObjects;

import java.util.ArrayList;

import Draws.BaseDraw;
import Draws.Drawable;
import ar.edu.itba.game.Side;

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

	@Override
	public void setCurrent(int current, float x, float y) {
		this.bar.setCurrent(current, x, y);
	}

	@Override
	public void setCurrent(int current) {
		this.bar.setCurrent(current);
	}

}

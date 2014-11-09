package ar.edu.itba.game.frontend.drawableobjects;

import java.util.ArrayList;

import ar.edu.itba.game.frontend.draws.Drawable;

public interface DrawableObject {
	
	/**
	 * returns an arraylist of drawables so that the Sprite Batch can draw them one by one
	 * @return
	 */
	public ArrayList<Drawable> getDraws();
	
	public void setCurrent(int current, float x, float y);
	
	public void setCurrent(int current);
}

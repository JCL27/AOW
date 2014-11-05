package DrawableObjects;

import java.util.ArrayList;

import Draws.Drawable;

public interface DrawableObject {
	public ArrayList<Drawable> getDraws();

	public void setCurrent(int current, float x, float y);
	
	public void setCurrent(int current);
}

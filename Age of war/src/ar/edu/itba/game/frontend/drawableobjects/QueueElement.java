package ar.edu.itba.game.frontend.drawableobjects;

import java.util.ArrayList;

import ar.edu.itba.game.backend.units.UnitType;
import ar.edu.itba.game.frontend.draws.Drawable;
import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;
/**
 * Represents one element of the queue, consists of a Bar and a drawable that represents the symbol of the unit that
 * is in queue
 *
 */
public class QueueElement implements DrawableObject {
	private Drawable icon;
	private Bar bar;
	private float xPos;
	private ArrayList<Drawable> draws = new ArrayList<Drawable>();
	
	/**
	 * Assign the correct image to the icon depending on witch class was passed as parameter
	 * and creates a Bar to track the remaining time to be created
	 * @param xPos
	 * @param yPos
	 * @param type
	 * @param creationTime
	 */
	public QueueElement(float xPos, float yPos, UnitType type, int creationTime){
		switch(type){
		case MELEE_UNIT:
			this.icon = new Icon(xPos, yPos, Textures.MELEE_UNIT_ICON);
			break;
		case RANGED_UNIT:
			this.icon = new Icon(xPos, yPos, Textures.RANGED_UNIT_ICON);			
			break;
		case FLYING_UNIT:
			this.icon = new Icon(xPos, yPos, Textures.FLYING_UNIT_ICON);			
			break;
		case ANTIAIRCRAFT_UNIT:
			this.icon = new Icon(xPos, yPos, Textures.ANTIAIRCRAFT_UNIT_ICON);			
			break;
		}
		bar = new Bar(creationTime, 1, 15, icon.getScreenWidth() ,(int)xPos, (int)yPos + icon.getScreenHeight());
		this.xPos = xPos;
		draws.addAll(bar.getDraws());
		draws.add(this.icon);
	}
	
	public float getxPos() {
		return xPos;
	}
	
	public void setxPos(float xPos) {
		this.xPos = xPos;
		for(Drawable draw: this.draws){
			draw.setxPos(xPos);
		}
	}
	/**
	 * Sets current remaining time
	 */
	public void setCurrent(int current){
		bar.setCurrent(current);
	}

	@Override
	public ArrayList<Drawable> getDraws() {
		return draws;
	}

	@Override
	public void setCurrent(int current, float x, float y) {
		bar.setCurrent(current, x, y);
	}
}
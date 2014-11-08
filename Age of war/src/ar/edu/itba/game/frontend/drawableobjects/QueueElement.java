package DrawableObjects;

import java.util.ArrayList;

import Draws.Drawable;
import Draws.Icon;
import Draws.Textures;

public class QueueElement implements DrawableObject {
	private Drawable icon;
	private Bar bar;
	private float xPos;
	private float yPos;
	private ArrayList<Drawable> draws = new ArrayList<Drawable>();
	
	public QueueElement(float xPos, float yPos, Class unitClass, int creationTime){
		String str = unitClass.getSimpleName();
		switch(str){
		case("MeleeUnit"):
			this.icon = new Icon(xPos, yPos, Textures.MELEE_UNIT_ICON);
			break;
		case("RangedUnit"):
			this.icon = new Icon(xPos, yPos, Textures.RANGED_UNIT_ICON);			
		break;
		case("AntiaircraftUnit"):
			this.icon = new Icon(xPos, yPos, Textures.ANTIAIRCRAFT_UNIT_ICON);			
		break;
		case("FlyingUnit"):
			this.icon = new Icon(xPos, yPos, Textures.FLYING_UNIT_ICON);			
		break;
		}
		bar = new Bar(creationTime, 1, 15, icon.getScreenWidth() ,(int)xPos, (int)yPos + icon.getScreenHeight());
		this.xPos = xPos;
		this.yPos = yPos;
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
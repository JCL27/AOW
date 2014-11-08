package DrawableObjects;

import java.util.ArrayList;

import Draws.BlackSquareDraw;
import Draws.Drawable;
import Draws.GreenSquareDraw;

public class Bar implements DrawableObject{
	private int max;
	private int current;
	private int screenWidth;
	private BlackSquareDraw black;
	private GreenSquareDraw green;
	private ArrayList<Drawable> draws = new ArrayList<Drawable>();
	
	public Bar(int max, int current, int screenHeight, int screenWidth, int xPos, int yPos){
		this.max = max;
		this.current = current;
		this.screenWidth = screenWidth;
		black = new BlackSquareDraw(xPos, yPos, screenHeight, screenWidth);
		green = new GreenSquareDraw(xPos, yPos, screenHeight, screenWidth);
		this.draws.add(black);
		this.draws.add(green);
	}
	
	public void setCurrent(int current, float xPos, float yPos){
		this.current = current;
		this.green.setScreenWidth(this.screenWidth * this.current / this.max);
		this.green.setxPos(xPos);
		this.black.setxPos(xPos);
	}
	
	public void setCurrent(int current){
		this.current = current;
		this.green.setScreenWidth(this.screenWidth * this.current / this.max);
	}
	
	@Override
	public ArrayList<Drawable> getDraws() {
		return this.draws;
	}
	
}

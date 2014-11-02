package Observers;

import java.util.Observable;
import java.util.Observer;

import Draws.BasicTowerDraw;
import UserInterface.UIManager;
import ar.edu.itba.game.Tower;

public class TowerObserver implements Observer{
	
	private Tower tower;
	private Draws.Drawable draw;
	
	public TowerObserver (Tower tower){
		this.tower = tower;
		System.out.println(tower.getHeight() + " " + tower.getWidth());
		this.draw = new BasicTowerDraw(tower.getX(), tower.getY(), tower.getHeight(), tower.getWidth(), tower.getPlayer());
		UserInterface.UIManager.getInstance().getDraws().add(this.draw);
		
	}
	@Override
	public void update(Observable o, Object arg) {
		this.draw.setxPos(tower.getX());
		this.draw.setyPos(tower.getY());
		
	}
	public void dispose() {
		UIManager.getInstance().getDraws().remove(this.draw);
	}
	
	

}

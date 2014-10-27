package Observers;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import Draws.RangedUnitDraw;
import Draws.TowerDraw;
import Units.Unit;
import ar.edu.itba.game.Tower;
import Draws.Drawable;
import UserInterface.UIManager;
import Draws.BasicTowerDraw;

public class TowerObserver implements Observer{
	
	private Tower tower;
	private Drawable draw;
	public TowerObserver (Tower tower){
		this.tower = tower;
		switch(tower.getClass().getSimpleName()){
		case("BasicTower"):
			this.draw = new BasicTowerDraw(tower.getX(), tower.getY(), tower.getHeight(), tower.getWidth(), tower.getPlayer());
			break;
		}
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

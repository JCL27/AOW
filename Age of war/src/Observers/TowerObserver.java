package Observers;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import Draws.MeleeUnitDraw;
import Draws.TowerDraw;
import ar.edu.itba.game.Tower;
import ar.edu.itba.game.Unit;

public class TowerObserver implements Observer{
	
	private Tower tower;
	private Drawable draw;
	
	public TowerObserver (Tower tower){
		this.tower = tower;
		//this.draw = new TowerDraw(tower.getX(), tower.getY(), tower.getWidth(), tower.getHeight(), tower.getPlayer());
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	

}

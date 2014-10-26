package Observers;

import java.util.Observable;
import java.util.Observer;

import Draws.BaseDraw;
import Draws.Drawable;
import UserInterface.UIManager;
import ar.edu.itba.game.Base;

public class BaseObserver implements Observer {
	private Base base;

	private Drawable draw;
	
	public BaseObserver(Base base){
		this.base = base;
		this.draw = new BaseDraw(base.getX(), base.getY(), (int)base.getHeight(), 256, base.getSide());
		
		UserInterface.UIManager.getInstance().getDraws().add(this.draw);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
	
	public void dispose() {
		UIManager.getInstance().getDraws().remove(this.draw);
	}
	
}

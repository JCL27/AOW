package Observers;

import java.util.Observable;
import java.util.Observer;

import Draws.BaseDraw;
import Draws.Drawable;
import UserInterface.Bar;
import UserInterface.UIManager;
import ar.edu.itba.game.Base;

public class BaseObserver implements Observer {
	private Base base;
	private Bar bar;
	private Drawable draw;
	
	public BaseObserver(Base base){
		this.base = base;
		this.draw = new BaseDraw(base.getX(), base.getY(), (int)base.getHeight(), 256, base.getSide());
		
		UserInterface.UIManager.getInstance().getDraws().add(this.draw);
		bar = new Bar(this.base.getMaxHP(), this.base.getMaxHP(), 30, this.base.getWidth(), (int)this.base.getX() + 35, (int) this.base.getY() + this.base.getHeight());
		UIManager.getInstance().getDOs().add(this.bar);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.bar.setCurrent(this.base.getHP());
	}
	
	public void dispose() {
		UIManager.getInstance().getDraws().remove(this.draw);
	}
	
}

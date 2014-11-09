package ar.edu.itba.game.frontend.observers;

import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.backend.logic.Tower;
import ar.edu.itba.game.frontend.draws.BasicTowerDraw;
import ar.edu.itba.game.frontend.userinterface.UIManager;

public class TowerObserver{

	public TowerObserver (){
	}

	public void createTower(Tower tower){
		BasicTowerDraw draw;
		if(tower.getSide().equals(Side.LEFT)){
			draw = new BasicTowerDraw(tower.getX(), tower.getY(), tower.getHeight(), tower.getWidth(), tower.getPlayer());
			UIManager.getInstance().setPlayerTower(draw);
			UIManager.getInstance().getDraws().add(UIManager.getInstance().getPlayerTower());
		}else{
			draw = new BasicTowerDraw(tower.getX(), tower.getY(), tower.getHeight(), tower.getWidth(), tower.getPlayer());
			UIManager.getInstance().setAITower(draw);
			UIManager.getInstance().getDraws().add(UIManager.getInstance().getAITower());
		}
	}

	public void dispose(Tower tower) {
		if(tower.getSide().equals(Side.LEFT))
			UIManager.getInstance().getDraws().remove(UIManager.getInstance().getPlayerTower());
		else
			UIManager.getInstance().getDraws().remove(UIManager.getInstance().getAITower());
	}



}

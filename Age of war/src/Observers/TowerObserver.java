package Observers;

import Draws.BasicTowerDraw;
import UserInterface.UIManager;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.Tower;

public class TowerObserver{

	public TowerObserver (){
		/*
		Tower tower = WorldManager.getInstance().getPlayer().getTower();
		BasicTowerDraw draw = new BasicTowerDraw(tower.getX(), tower.getY(), tower.getHeight(), tower.getWidth(), tower.getPlayer());
		UIManager.getInstance().setPlayerTower(draw);
		tower = WorldManager.getInstance().getPlayerAI().getTower();
		draw = new BasicTowerDraw(tower.getX(), tower.getY(), tower.getHeight(), tower.getWidth(), tower.getPlayer());
		UIManager.getInstance().setPlayerTower(draw);*/
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

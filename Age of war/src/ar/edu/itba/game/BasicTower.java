package ar.edu.itba.game;

import Observers.TowerObserver;

public class BasicTower extends Tower {
	
	
	public BasicTower(Player player){
		this.player = player;
		this.objective = null;
		this.attackFlying = true;
		
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
		this.damage = 200;
		this.cost = 200;
		this.cooldown = 0;
		this.side = Side.LEFT;
		this.attackSpeed = 5;
		this.attackRange = 600;
		this.element = new Element(50, GameStats.TOWER_HEIGHT + Game.GROUND_HEIGHT, 0, 0, 100, 100, false);
		} 
		else{
			this.damage = 200;
			this.cost = 200;
			this.cooldown = 0;
			this.side = Side.RIGHT;
			this.attackSpeed = 5;
			this.attackRange = 600;
			this.element = new Element(1000, GameStats.TOWER_HEIGHT + Game.GROUND_HEIGHT, 0, 0, 100, 100, false);
		}
	
		this.observer = new TowerObserver(this);
		this.addObserver(this.observer);
		WorldManager.getInstance().getElements().add(this.element);
		
	}
	
	
}

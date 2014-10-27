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
		this.cooldown = 200;
		this.side = Side.LEFT;
		this.attackSpeed = 1;
		this.attackRange = 700;
		this.element = new Element(50, Game.FLYING_HEIGHT + Game.GROUND_HEIGHT, 3, 0, 200, 100, false);
		}
		else{
			this.damage = 200;
			this.cost = 200;
			this.cooldown = 200;
			this.side = Side.RIGHT;
			this.attackSpeed = 1;
			this.attackRange = 700;
			this.element = new Element(1000, Game.FLYING_HEIGHT + Game.GROUND_HEIGHT, 3, 0, 200, 100, false);
		}
	
		this.observer = new TowerObserver(this);
		this.addObserver(this.observer);
		WorldManager.getInstance().getElements().add(this.element);
		
	}
	
	
}

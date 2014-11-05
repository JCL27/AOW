package Upgrades;

import java.util.ArrayList;

import Units.Unit;
import ar.edu.itba.game.Player;
import exceptions.NotEnoughExpException;
import exceptions.UnavailableUpgradeException;

public abstract class Upgrade {
	protected ArrayList<Upgrade> needs;
	protected ArrayList<Upgrade> allows;
	
	protected boolean applied = false;
	protected boolean available;
	protected boolean multiUpgradable;
	protected int cost;
	protected Player player;
	
	public abstract void applyUpgrade(Class<Unit> classType);
	
	public Upgrade(Player player){
		this.player = player;
	}
	
	public void chargeAndApply(Class<Unit> classType) throws UnavailableUpgradeException, NotEnoughExpException {
		if (this.isAvailable() == false)
			throw new UnavailableUpgradeException();
		useExp();
		applyUpgrade(classType);
		this.applied = true;
	}
	
	public void setAvailable(){
		this.available = true;
	}
	
	public void setUnavailable(){
		this.available = false;
	}
	
	public void useExp() throws NotEnoughExpException{
		this.player.useExp(this.cost);
	}
	
	public void checkNewUpgrades(){
		for(Upgrade upgrade: this.allows){
			if(upgrade.available == false){
				upgrade.available = true;
				for(Upgrade need:upgrade.needs){
					if(need.available == false){
						upgrade.available = false;
					}
				}
			}
		}
	}
	
	public boolean isAvailable(){
		if(multiUpgradable){
			return available;
		}else{
			return (available && !applied);
		}
	}
	
	public int getCost(){
		return this.cost;
	}
}

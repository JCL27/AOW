package Upgrades;

import java.util.ArrayList;

import exceptions.NotEnoughGoldException;
import exceptions.UnavailableUpgradeException;
import ar.edu.itba.game.Player;

public abstract class Upgrade {
	protected ArrayList<Upgrade> needs;
	protected ArrayList<Upgrade> allows;
	
	protected boolean applied;
	protected boolean available;
	protected boolean multiUpgradable;
	protected int cost;
	protected Player player;
	
	public abstract void applyUpgrade(Class classType);
	
	public Upgrade(Player player){
		this.player = player;
	}
	
	public void chargeAndApply(Class classType) throws UnavailableUpgradeException{
		try{
			if (this.available == false)
				throw new UnavailableUpgradeException();
			charge(player);
			applyUpgrade(classType);
		}catch(NotEnoughGoldException e){
			//avisar que no se puede
		}
	}
	
	public void setAvailable(){
		this.available = true;
	}
	
	public void setUnavailable(){
		this.available = false;
	}
	
	public void charge(Player player) throws NotEnoughGoldException{
		player.charge(this.cost);
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

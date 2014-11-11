package ar.edu.itba.game.backend.upgrades;

import java.util.ArrayList;

import ar.edu.itba.game.backend.exceptions.NotEnoughExpException;
import ar.edu.itba.game.backend.exceptions.UnavailableUpgradeException;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.units.Unit;

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
	
	
	/**
	 * Checks if the player can afford the upgrade, and then calls the 
	 * method that applies
	 * @param classType
	 * @throws UnavailableUpgradeException
	 * @throws NotEnoughExpException
	 */
	public void chargeAndApply(Class<Unit> classType) throws UnavailableUpgradeException, NotEnoughExpException {
		if (this.isAvailable() == false)
			throw new UnavailableUpgradeException();
		useExp();
		applyUpgrade(classType);
		this.applied = true;
	}
	
	public void setAvailable(){
		this.available = true;
		this.applied = false;
	}
	
	public void setUnavailable(){
		this.available = false;
	}
	
	public void useExp() throws NotEnoughExpException{
		this.player.useExp(this.cost);
		System.out.println("Upgrade: " + player.getExp());
	}
	
	/**
	 * Checks which upgrade depends directly from this in the upgrade tree,
	 * and if it doesn't need any other upgrade to be enabled, set it enabled
	 */
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
	
	
	/**
	 * Checks if the upgrade can be applied, if it is multiupgradable, it doesn't check if it is already applied
	 * @return
	 */
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

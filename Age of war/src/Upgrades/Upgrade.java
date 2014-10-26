package Upgrades;

import java.util.ArrayList;

import ar.edu.itba.game.Player;

public abstract class Upgrade {
	protected ArrayList<Upgrade> needs;
	protected ArrayList<Upgrade> allows;
	
	protected boolean applied;
	protected boolean available;
	
	public abstract void applyUpgrade(Player player);
	
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
}

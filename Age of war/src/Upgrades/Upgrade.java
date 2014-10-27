package Upgrades;

import java.util.ArrayList;

import exceptions.NotEnoughGoldException;
import ar.edu.itba.game.Player;

public abstract class Upgrade {
	protected ArrayList<Upgrade> needs;
	protected ArrayList<Upgrade> allows;
	
	protected boolean applied;
	protected boolean available;
	protected int cost;
	
	public abstract void applyUpgrade(Player player);
	
	public void chargeAndApply(Player player){
		try{
			charge(player);
			applyUpgrade(player);
		}catch(NotEnoughGoldException e){
			//avisar que no se puede
		}
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
}

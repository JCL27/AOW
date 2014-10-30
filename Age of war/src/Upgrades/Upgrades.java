package Upgrades;

import java.util.HashMap;

import ar.edu.itba.game.Player;

public class Upgrades {
	
	private static Upgrades instance = null;
	private HashMap<String, Upgrade> upgradeList = new HashMap<String, Upgrade>();
	
	private Upgrades(){
		this.upgradeList.put("MeleeUnitUpgrade", new MeleeUnitUpgrade());
	}
	
	public static Upgrades getInstance() {
		if(instance == null) {
			instance = new Upgrades();
		}
		return instance;
	}
	
	public void applyUpgrade(String name, Player player){
		upgradeList.get(name).applyUpgrade(player);
	}
	
}

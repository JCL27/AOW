package Upgrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import ar.edu.itba.game.Player;

public class Upgrades {
	
	private static Upgrades instance = null;
	
	private HashMap<String, Upgrade> upgradeListPlayer = new HashMap<String, Upgrade>();
	private HashMap<String, Upgrade> upgradeListPlayerAI = new HashMap<String, Upgrade>();
	
	private Upgrades(){
		this.upgradeListPlayer.put("MeleeUnitUpgrade", new MeleeUnitUpgrade());
		this.upgradeListPlayerAI.put("MeleeUnitUpgrade", new MeleeUnitUpgrade());
	}
	
	public static Upgrades getInstance() {
		if(instance == null) {
			instance = new Upgrades();
		}
		return instance;
	}
	
	public void applyUpgrade(String name, Player player){
		upgradeListPlayer.get(name).applyUpgrade(player);
	}
	
	public ArrayList<Upgrade> getAIAvailableUpgrades(){
		ArrayList<Upgrade> availables = new ArrayList<Upgrade>();
		for(Upgrade upgrade:upgradeListPlayerAI.values()){
			if(upgrade.available)
				availables.add(upgrade);
		}
		return availables;
	}
}

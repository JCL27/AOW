package ar.edu.itba.game;

import java.util.HashMap;

public class Upgrades {
	
	private static Upgrades instance = null;
	private static HashMap<String, Upgrade> upgradeList = new HashMap<String, Upgrade>();
	
	private Upgrades(){
	}
	
	public static Upgrades getInstance() {
		if(instance == null) {
			instance = new Upgrades();
		}
		return instance;
	}
	
	public void applyUpgrade(String name){
		upgradeList.get(name).applyUpgrade();
	}
	
	public void UpgradeMeleeUnitDamage(Player player){
		if(player.equals(WorldManager.getInstance().getPlayer())){
			MeleeUnit.getPlayerUS().upgradeDamage();
		}
	}
}

package Upgrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class Upgrades {
	
	private static Upgrades instance = null;
	
	private Player player = WorldManager.getInstance().getPlayer();
	private Player playerAI = WorldManager.getInstance().getplayerAI();
	
	private HashMap<String, Upgrade> upgradeListPlayer = new HashMap<String, Upgrade>();
	private HashMap<String, Upgrade> upgradeListPlayerAI = new HashMap<String, Upgrade>();
	
	private Upgrades(){
		this.upgradeListPlayer.put("UnitUpgrade", new UnitUpgrade(player));
		this.upgradeListPlayerAI.put("UnitUpgrade", new UnitUpgrade(playerAI));
		this.upgradeListPlayer.put("TowerDamageUpgrade", new TowerDamageUpgrade(player));
		this.upgradeListPlayerAI.put("TowerDamageUpgrade", new TowerDamageUpgrade(playerAI));
		this.upgradeListPlayer.put("TowerAttackSpeedUpgrade", new TowerAttackSpeedUpgrade(player));
		this.upgradeListPlayerAI.put("TowerAttackSpeedUpgrade", new TowerAttackSpeedUpgrade(player));
		this.upgradeListPlayer.put("TowerAttackRangeUpgrade", new TowerAttackRangeUpgrade(player));
		this.upgradeListPlayerAI.put("TowerAttackRangeUpgrade", new TowerAttackRangeUpgrade(player));
	}
	
	public static Upgrades getInstance() {
		if(instance == null) {
			instance = new Upgrades();
		}
		return instance;
	}
	
	public void applyUpgrade(String name, Player player){
		upgradeListPlayer.get(name).applyUpgrade(null);
	}
	
	public void applyUpgrade(String name, Player player, Class classType){
		upgradeListPlayer.get(name).applyUpgrade(classType);
	}
	
	public ArrayList<Upgrade> getAIAvailableUpgrades(){
		ArrayList<Upgrade> availables = new ArrayList<Upgrade>();
		for(Upgrade upgrade:upgradeListPlayerAI.values()){
			if(upgrade.available)
				availables.add(upgrade);
		}
		return availables;
	}
	
	public void setAvailable(String name, Player player){
		if(this.player.equals(player))
			this.upgradeListPlayer.get(name).setAvailable();
		else
			this.upgradeListPlayerAI.get(name).setAvailable();
	}
	
	public void setUnavailable(String name, Player player){
		if(this.player.equals(player))
			this.upgradeListPlayer.get(name).setUnavailable();
		else
			this.upgradeListPlayerAI.get(name).setUnavailable();
	}
	
}

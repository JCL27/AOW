package ar.edu.itba.game.backend.upgrades;

import java.util.ArrayList;
import java.util.HashMap;

import ar.edu.itba.game.backend.exceptions.NotEnoughExpException;
import ar.edu.itba.game.backend.exceptions.UnavailableUpgradeException;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;

public class Upgrades {
	
	private static Upgrades instance = null;
	
	private Player player = WorldManager.getInstance().getPlayer();
	private Player playerAI = WorldManager.getInstance().getPlayerAI();
	
	private HashMap<String, Upgrade> upgradeListPlayer = new HashMap<String, Upgrade>();
	private HashMap<String, Upgrade> upgradeListPlayerAI = new HashMap<String, Upgrade>();
	
	/**
	 * Add an instance of every upgrade to two HashMaps, one for the human player, and one for the AI
	 * Every entry have its value's simple name as a key
	 */
	private Upgrades(){
		this.upgradeListPlayer.put("UnitUpgrade", new UnitUpgrade(player));
		this.upgradeListPlayerAI.put("UnitUpgrade", new UnitUpgrade(playerAI));
		this.upgradeListPlayer.put("TowerDamageUpgrade", new TowerDamageUpgrade(player));
		this.upgradeListPlayerAI.put("TowerDamageUpgrade", new TowerDamageUpgrade(playerAI));
		this.upgradeListPlayer.put("TowerAttackSpeedUpgrade", new TowerAttackSpeedUpgrade(player));
		this.upgradeListPlayerAI.put("TowerAttackSpeedUpgrade", new TowerAttackSpeedUpgrade(playerAI));
		this.upgradeListPlayer.put("TowerAttackRangeUpgrade", new TowerAttackRangeUpgrade(player));
		this.upgradeListPlayerAI.put("TowerAttackRangeUpgrade", new TowerAttackRangeUpgrade(playerAI));
		this.upgradeListPlayer.put("AntiaircraftUnitResearch", new AntiaircraftUnitResearch(player));
		this.upgradeListPlayerAI.put("AntiaircraftUnitResearch", new AntiaircraftUnitResearch(playerAI));
		this.upgradeListPlayer.put("FlyingUnitResearch", new FlyingUnitResearch(player));
		this.upgradeListPlayerAI.put("FlyingUnitResearch", new FlyingUnitResearch(playerAI));
	}
	
	public static Upgrades getInstance() {
		if(instance == null) {
			instance = new Upgrades();
		}
		return instance;
	}
	
	public void applyUpgrade(String name, Player player) throws UnavailableUpgradeException, NotEnoughExpException{
		this.applyUpgrade(name, player, null);
	}
	
	public void applyUpgrade(String name, Player player, Class classType) throws UnavailableUpgradeException, NotEnoughExpException{
		if(this.player.equals(player)){
			this.upgradeListPlayer.get(name).chargeAndApply(classType);
		}else{
			this.upgradeListPlayerAI.get(name).chargeAndApply(classType);
		}
	}
	
	/**
	 * returns a upgrade list from witch the AI can choose to decide what to upgrade
	 * @return
	 */
	public ArrayList<Upgrade> getAIAvailableUpgrades(){
		ArrayList<Upgrade> availables = new ArrayList<Upgrade>();
		for(Upgrade upgrade:upgradeListPlayerAI.values()){
			if(upgrade.available)
				availables.add(upgrade);
		}
		return availables;
	}
	
	public boolean isAvailable(Class upgradeClass, Player player){
		if(this.player.equals(player))
			return this.upgradeListPlayer.get(upgradeClass.getSimpleName()).isAvailable();
		else
			return this.upgradeListPlayerAI.get(upgradeClass.getSimpleName()).isAvailable();
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

	public static void reset() {
		instance = null;
	}
	
}

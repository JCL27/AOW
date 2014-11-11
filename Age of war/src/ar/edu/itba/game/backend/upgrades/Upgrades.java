package ar.edu.itba.game.backend.upgrades;

import java.util.ArrayList;
import java.util.HashMap;

import ar.edu.itba.game.backend.exceptions.NotEnoughExpException;
import ar.edu.itba.game.backend.exceptions.UnavailableUpgradeException;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.Tower;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.UnitType;

public class Upgrades {
	
	private static Upgrades instance = null;
	
	private Player player = WorldManager.getInstance().getPlayer();
	private Player playerAI = WorldManager.getInstance().getPlayerAI();
	
	private HashMap<UpgradeType, Upgrade> upgradeListPlayer = new HashMap<UpgradeType, Upgrade>();
	private HashMap<UpgradeType, Upgrade> upgradeListPlayerAI = new HashMap<UpgradeType, Upgrade>();
	
	/**
	 * Add an instance of every upgrade to two HashMaps, one for the human player, and one for the AI
	 * Every entry have its value's simple name as a key
	 */
	private Upgrades(){
		
		this.upgradeListPlayer.put(UpgradeType.UNIT_UPGRADE, new UnitUpgrade(player));
		this.upgradeListPlayerAI.put(UpgradeType.UNIT_UPGRADE, new UnitUpgrade(playerAI));
		this.upgradeListPlayer.put(UpgradeType.TOWER_DAMAGE_UPGRADE, new TowerDamageUpgrade(player));
		this.upgradeListPlayerAI.put(UpgradeType.TOWER_DAMAGE_UPGRADE, new TowerDamageUpgrade(playerAI));
		this.upgradeListPlayer.put(UpgradeType.TOWER_ATTACK_SPEED_UPGRADE, new TowerAttackSpeedUpgrade(player));
		this.upgradeListPlayerAI.put(UpgradeType.TOWER_ATTACK_SPEED_UPGRADE, new TowerAttackSpeedUpgrade(playerAI));
		this.upgradeListPlayer.put(UpgradeType.TOWER_ATTACK_RANGE_UPGRADE, new TowerAttackRangeUpgrade(player));
		this.upgradeListPlayerAI.put(UpgradeType.TOWER_ATTACK_RANGE_UPGRADE, new TowerAttackRangeUpgrade(playerAI));
		this.upgradeListPlayer.put(UpgradeType.ANTIAIRCRAFT_UNIT_RESEARCH, new AntiaircraftUnitResearch(player));
		this.upgradeListPlayerAI.put(UpgradeType.ANTIAIRCRAFT_UNIT_RESEARCH, new AntiaircraftUnitResearch(playerAI));
		this.upgradeListPlayer.put(UpgradeType.FLYING_UNIT_RESEARCH, new FlyingUnitResearch(player));
		this.upgradeListPlayerAI.put(UpgradeType.FLYING_UNIT_RESEARCH, new FlyingUnitResearch(playerAI));
	
		
	}
	
	public static Upgrades getInstance() {
		if(instance == null) {
			instance = new Upgrades();
		}
		return instance;
	}
	
	public void initializeUpgrades(){
		Tower tower = WorldManager.getInstance().getPlayer().getTower();
		if (tower != null){
			Upgrades.getInstance().setAvailable(UpgradeType.TOWER_DAMAGE_UPGRADE, this.player);
			Upgrades.getInstance().setAvailable(UpgradeType.TOWER_ATTACK_SPEED_UPGRADE, this.player);
			Upgrades.getInstance().setAvailable(UpgradeType.TOWER_ATTACK_RANGE_UPGRADE, this.player);
			if(tower.isUpgradedDamage())
				Upgrades.getInstance().setUnapplied(UpgradeType.TOWER_DAMAGE_UPGRADE, this.player);
			if(tower.isUpgradedAttackSpeed())
				Upgrades.getInstance().setUnapplied(UpgradeType.TOWER_ATTACK_SPEED_UPGRADE, this.player);
			if(tower.isUpgradedAttackRange())
				Upgrades.getInstance().setUnapplied(UpgradeType.TOWER_ATTACK_RANGE_UPGRADE, this.player);
		}
		tower = WorldManager.getInstance().getPlayerAI().getTower();
		if (tower != null){
			Upgrades.getInstance().setAvailable(UpgradeType.TOWER_DAMAGE_UPGRADE, this.playerAI);
			Upgrades.getInstance().setAvailable(UpgradeType.TOWER_ATTACK_SPEED_UPGRADE, this.playerAI);
			Upgrades.getInstance().setAvailable(UpgradeType.TOWER_ATTACK_RANGE_UPGRADE, this.playerAI);
			if(tower.isUpgradedDamage())
				Upgrades.getInstance().setUnapplied(UpgradeType.TOWER_DAMAGE_UPGRADE, this.playerAI);
			if(tower.isUpgradedAttackSpeed())
				Upgrades.getInstance().setUnapplied(UpgradeType.TOWER_ATTACK_SPEED_UPGRADE, this.playerAI);
			if(tower.isUpgradedAttackRange())
				Upgrades.getInstance().setUnapplied(UpgradeType.TOWER_ATTACK_RANGE_UPGRADE, this.playerAI);
		}
	}
	
	public void applyUpgrade(UpgradeType upgrade, Player player) throws UnavailableUpgradeException, NotEnoughExpException{
		this.applyUpgrade(upgrade, player, null);
	}
	
	public void applyUpgrade(UpgradeType upgrade, Player player, UnitType type) throws UnavailableUpgradeException, NotEnoughExpException{
		if(this.player.equals(player)){
			this.upgradeListPlayer.get(upgrade).chargeAndApply(type);
		}else{
			this.upgradeListPlayerAI.get(upgrade).chargeAndApply(type);
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
	
	public boolean isAvailable(UpgradeType upgrade, Player player){
		if(this.player.equals(player))
			return this.upgradeListPlayer.get(upgrade).isAvailable();
		else
			return this.upgradeListPlayerAI.get(upgrade).isAvailable();
	}	
	
	public void setAvailable(UpgradeType upgrade, Player player){
		if(this.player.equals(player))
			this.upgradeListPlayer.get(upgrade).setAvailable();
		else
			this.upgradeListPlayerAI.get(upgrade).setAvailable();
	}
	
	public void setUnavailable(UpgradeType upgrade, Player player){
		if(this.player.equals(player))
			this.upgradeListPlayer.get(upgrade).setUnavailable();
		else
			this.upgradeListPlayerAI.get(upgrade).setUnavailable();
	}
	
	public void setApplied(UpgradeType upgrade, Player player){
		if(this.player.equals(player))
			this.upgradeListPlayer.get(upgrade).setApplied();
		else
			this.upgradeListPlayerAI.get(upgrade).setApplied();
	}
	
	public void setUnapplied(UpgradeType upgrade, Player player){
		if(this.player.equals(player))
			this.upgradeListPlayer.get(upgrade).setUnapplied();
		else
			this.upgradeListPlayerAI.get(upgrade).setUnapplied();
	}
	
	public static void reset() {
		instance = null;
	}
	
}

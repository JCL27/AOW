package ar.edu.itba.game.backend.logic;

import java.io.Serializable;
import java.util.ArrayList;

import ar.edu.itba.game.backend.exceptions.NotEnoughExpException;
import ar.edu.itba.game.backend.exceptions.NotEnoughGoldException;
import ar.edu.itba.game.backend.exceptions.UnavailableUpgradeException;
import ar.edu.itba.game.backend.units.AntiaircraftUnit;
import ar.edu.itba.game.backend.units.FlyingUnit;
import ar.edu.itba.game.backend.units.MeleeUnit;
import ar.edu.itba.game.backend.units.RangedUnit;
import ar.edu.itba.game.backend.units.Unit;
import ar.edu.itba.game.backend.units.UnitType;
import ar.edu.itba.game.backend.units.UnitsLevels;
import ar.edu.itba.game.backend.upgrades.UpgradeType;
import ar.edu.itba.game.backend.upgrades.Upgrades;
import ar.edu.itba.game.frontend.observers.PlayerObserver;

/**
 * Player has got units, projectiles, a tower, a base, gold and experience
 *
 */
public class Player implements Serializable{

	private static final long serialVersionUID = -3361130881364588943L;
	private Integer gold;
	private Integer experience;
	private ArrayList<Unit> units;
	private ArrayList<Projectile> projectiles;
	private Tower tower;
	private Base base;
	private ArrayList<UnitType> unitsQueue;
	private boolean playerCreatingUnit = false;
	private int playerUnitCreationTime = 0;
	private transient PlayerObserver observer;
	private UnitType unitToQueue = null;
	private Side side;

	public Player (Side side, PlayerObserver playerObserver){
		this.gold = GameStats.INITIAL_GOLD;
		this.experience = GameStats.INITIAL_EXP;
		this.base = Factory.getInstance().createBase(side);
		this.units = new ArrayList<Unit>();
		this.projectiles = new ArrayList<Projectile>();
		this.unitsQueue = new ArrayList<UnitType>();
		this.observer = playerObserver;
		this.side = side;
	}

	public ArrayList<Projectile> getProjectiles(){
		return this.projectiles;
	}

	public ArrayList<Unit> getUnits(){
		return units;
	}

	public void research(UpgradeType upgrade){

		this.research(upgrade, null);
	}

	/**
	 * Researches an upgrade passed by parameter, and applies it to the second class passed by parameter
	 * if the upgrade allows that
	 * @param class1
	 * @param type
	 */
	public void research(UpgradeType upgrade, UnitType type){
		try {
			Upgrades.getInstance().applyUpgrade(upgrade, this, type);
		} catch (UnavailableUpgradeException e) {
			e.printStackTrace();
		} catch (NotEnoughExpException e) {
			e.printStackTrace();
		}
	}

	public Side getSide() {
		return side;
	}

	/**
	 * Check if the player is able to buy the unit (using reflection), and adds it to the queue, unless it reach the limit
	 * @param unitClass
	 * @return
	 */
	public boolean buyUnit(UnitType type){
		Integer unitCost = null;
		boolean created = true;
		boolean available = true;
		if(this.equals(WorldManager.getInstance().getPlayer())){
			if(type.equals(UnitType.ANTIAIRCRAFT_UNIT))
				available = UnitsLevels.getInstance().isPlayerAntiaircraftUnitAvailable();
			else if(type.equals(UnitType.FLYING_UNIT))
				available = UnitsLevels.getInstance().isPlayerFlyingUnitAvailable();
		}else{
			if(type.equals(UnitType.ANTIAIRCRAFT_UNIT))
				available = UnitsLevels.getInstance().isAIAntiaircraftUnitAvailable();
			else if(type.equals(UnitType.FLYING_UNIT))
				available = UnitsLevels.getInstance().isAIFlyingUnitAvailable();
		}
		if(available && this.unitsQueue.size()<5){
			unitCost = UnitsLevels.getInstance().getCost(this, type);

			try{
				this.charge(unitCost);
				this.unitsQueue.add(type);
				if(this.observer!=null)
					this.observer.addElementToQueue(this, type);
			}catch(NotEnoughGoldException e){
				created = false;
				e.printStackTrace();	
			}
		}
		return created;
	}


	public void createUnit(UnitType type){
		Unit unit = Factory.getInstance().createUnit(type, this);
		if(unit!=null){
			this.units.add(unit);
		}
	}


	public boolean buyTower(){
		boolean created = true;
		try {
			this.charge(GameStats.TOWER_COST);
			this.createTower();
		} catch (NotEnoughGoldException e) {
			created = false;
			e.printStackTrace();
		}
		return created;
	}

	public void createTower(){
		this.tower = Factory.getInstance().createTower(this);
	}

	public void addGold(int gold){
		this.gold += gold;
	}

	public void charge(int gold) throws NotEnoughGoldException{
		if(this.gold < gold)
			throw new NotEnoughGoldException();
		this.gold -= gold;
	}

	public void useExp(int exp) throws NotEnoughExpException{
		if (this.experience < exp){
			System.out.println("player: "+ exp +  " " + this.experience);
			throw new NotEnoughExpException();
		}
		this.experience -= exp;
	}

	public void addExp(int exp){
		this.experience += exp;
	}

	public ArrayList<UnitType> getUnitsQueue() {
		return unitsQueue;
	}

	public Integer getGold(){
		return this.gold;
	}

	public Integer getExp(){
		return this.experience;
	}

	public Tower getTower(){
		return this.tower;
	}

	public void sellTower(){
		if(this.tower != null){
			Upgrades.getInstance().setUnavailable(UpgradeType.TOWER_DAMAGE_UPGRADE, this);
			Upgrades.getInstance().setUnavailable(UpgradeType.TOWER_ATTACK_SPEED_UPGRADE, this);
			Upgrades.getInstance().setUnavailable(UpgradeType.TOWER_ATTACK_RANGE_UPGRADE, this);
			this.tower.Sell();
		}	
	}

	public Base getBase(){
		return this.base;
	}

	@Override
	public String toString() {
		return "Player [gold=" + gold + ", experience=" + experience
				+ ", units=" + units + ", projectiles=" + projectiles + "]";
	}

	public void disposeTower() {
		this.tower = null;
	}

	/**
	 * Manages the queue, reducing the remaining time in each render, and instancing the unit when it reaches 0
	 */
	public void updateQueue(){
		if(!this.getUnitsQueue().isEmpty()){

			if(this.playerCreatingUnit && this.playerUnitCreationTime == 0){
				this.getUnitsQueue().remove(0);
				this.createUnit(unitToQueue);
				this.playerCreatingUnit = false;
				if(this.observer!=null)
					this.observer.removeElementFromQueue(this, 0);
			}else if(this.playerCreatingUnit){
				this.playerUnitCreationTime--;
				if(this.observer!=null)
					this.observer.updateCurrentTime(this, this.playerUnitCreationTime);
			}else{
				this.unitToQueue = this.getUnitsQueue().get(0);
				switch(unitToQueue){
				case MELEE_UNIT:
					this.playerUnitCreationTime = MeleeUnit.getCreationTime();
					break;
				case RANGED_UNIT:
					this.playerUnitCreationTime = RangedUnit.getCreationTime();
					break;
				case FLYING_UNIT:
					this.playerUnitCreationTime = FlyingUnit.getCreationTime();
					break;
				case ANTIAIRCRAFT_UNIT:
					this.playerUnitCreationTime = AntiaircraftUnit.getCreationTime();
					break;
				}
				this.playerCreatingUnit = true;
			}
		}
	}

	public UnitType getUnitToQueue() {
		return unitToQueue;
	}

	public int getPlayerUnitCreationTime() {
		return playerUnitCreationTime;
	}

	/**
	 * Set the observer and updates the observer
	 * @param playerObserver
	 */
	public void setObserver(PlayerObserver playerObserver) {
		this.observer = playerObserver;
		if(this.observer!=null)
			this.observer.loadQueue(this, this.unitsQueue, this.playerUnitCreationTime);
	}
}

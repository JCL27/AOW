package ar.edu.itba.game.backend.logic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ar.edu.itba.game.backend.units.Unit;
import ar.edu.itba.game.frontend.observers.BaseObserver;
import ar.edu.itba.game.frontend.observers.PlayerObserver;
import ar.edu.itba.game.frontend.observers.ProjectileObserver;
import ar.edu.itba.game.frontend.observers.TowerObserver;
import ar.edu.itba.game.frontend.observers.UnitObserver;
/**
 * Instance the units, projectiles, bases and players
 * 
 *
 */
public class Factory {

	private static Factory instance = null;
	private UnitObserver unitObserver;
	private TowerObserver towerObserver;
	private PlayerObserver playerObserver;
	private BaseObserver baseObserver;
	private ProjectileObserver projectileObserver;


	public static Factory getInstance() {
		if(instance == null) {
			instance = new Factory();
		}
		return instance;
	}

	private Factory(){
	}

	/**
	 * Get the observers by parameters, and store a reference to them (it isn't necessary)
	 * @param baseObserver
	 * @param unitObserver
	 * @param playerObserver
	 * @param towerObserver
	 * @param projectileObserver
	 */
	public void setObservers(BaseObserver baseObserver, UnitObserver unitObserver, 
			PlayerObserver playerObserver, TowerObserver towerObserver, ProjectileObserver projectileObserver){
		this.unitObserver = unitObserver;
		this.baseObserver = baseObserver;
		this.towerObserver = towerObserver;
		this.playerObserver = playerObserver;
		this.projectileObserver = projectileObserver;
	}
	/**
	 * 
	 * @param side
	 * @return
	 */
	public Player createPlayer(Side side){
		Player player = new Player(side, this.playerObserver);
		return player;
	}

	/**
	 * instance a projectile and pass the projectileobserver as a parameter
	 * @param X
	 * @param Y
	 * @param velX
	 * @param velY
	 * @param gravity
	 * @param damage
	 * @return
	 */
	public Projectile createProjectile(float X, float Y, float velX, float velY, boolean gravity, int damage){
		Projectile pjt = new Projectile(X, Y, velX, velY, gravity, damage, this.projectileObserver);
		if(this.projectileObserver!=null)
			this.projectileObserver.addProjectile(pjt);
		return pjt;
	}

	/**
	 * instance a tower and pass the towerobserver as a parameter
	 * @param player
	 * @return
	 */
	public Tower createTower(Player player){
		Tower tower = new Tower(player, this.towerObserver);
		if (this.towerObserver!=null)
			towerObserver.createTower(tower);
		return tower;
	}

	/**
	 * instance a base and pass the baseobserver as a parameter
	 * @param side
	 * @return
	 */
	public Base createBase(Side side){
		Base base = new Base(side, this.baseObserver);
		return base;
	}

	/**
	 * Receives a unitClass, instance a unit using reflection and assign unitobserver to it
	 * @param unitClass
	 * @param player
	 * @return
	 */
	public Unit createUnit(Class unitClass, Player player){
		Unit unit = null;
		try {
			Constructor cons = unitClass.getConstructor(new Class[] { Player.class, UnitObserver.class});
			unit = (Unit) cons.newInstance(new Object[] {player, this.unitObserver});
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if(this.unitObserver!=null)
			this.unitObserver.addUnit(unit);
		return unit;
	}


	/**
	 * Reassign the observer to the currently instanced units, projectiles, players, bases and towers
	 */
	public void reAssignObservers(){

		Player player = WorldManager.getInstance().getPlayer();
		Player playerAI = WorldManager.getInstance().getPlayerAI();

		if(this.unitObserver!=null && this.towerObserver!=null && this.playerObserver!=null &&
				this.projectileObserver!=null && this.baseObserver!=null){
			for(Unit unit:WorldManager.getInstance().getPlayer().getUnits()){
				unit.setObserver(this.unitObserver);
				this.unitObserver.addUnit(unit);
			}
			for(Unit unit:playerAI.getUnits()){
				unit.setObserver(this.unitObserver);
				this.unitObserver.addUnit(unit);
			}
			for(Projectile pjt:player.getProjectiles()){
				pjt.setObserver(this.projectileObserver);
				projectileObserver.addProjectile(pjt);
			}
			for(Projectile pjt:playerAI.getProjectiles()){
				pjt.setObserver(this.projectileObserver);
				projectileObserver.addProjectile(pjt);
			}
			player.getBase().setObserver(this.baseObserver);
			playerAI.getBase().setObserver(this.baseObserver);
			if(player.getTower()!=null)
				player.getTower().setObserver(this.towerObserver);
			if(playerAI.getTower()!=null)
				playerAI.getTower().setObserver(this.towerObserver);
			player.setObserver(this.playerObserver);
			playerAI.setObserver(this.playerObserver);
			if(player.getTower()!=null)
				this.towerObserver.createTower(player.getTower());
			if(playerAI.getTower()!=null)
				this.towerObserver.createTower(playerAI.getTower());
		}
	}


}

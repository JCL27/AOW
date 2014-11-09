package ar.edu.itba.game.backend.logic;


import java.io.Serializable;
import java.util.ArrayList;

import ar.edu.itba.game.backend.units.Unit;

public class WorldManager implements Serializable{

	private static final long serialVersionUID = -4035477768723084005L;
	public static float MINDISTANCE = 25;

	private Player player;
	private Player playerAI;
	private ArrayList<Element> elements = new ArrayList<Element>();
	private ArrayList<Unit> unitsToKill = new ArrayList<Unit>();
	private Element ground;

	private static WorldManager instance = null;

	private WorldManager() {
		player = Factory.getInstance().createPlayer(Side.LEFT);
		playerAI = Factory.getInstance().createPlayer(Side.RIGHT);
		ground = new Element(-Game.WIDTH * 10, 0, Game.WIDTH * 21,  Game.GROUND_HEIGHT);
	}

	public static WorldManager getInstance() {
		if(instance == null) {
			instance = new WorldManager();
		}
		return instance;
	}

	public void notifyObservers(){
		for(Unit unit:player.getUnits()){
			unit.notifyObservers();
		}
		for(Unit unit:playerAI.getUnits())
			unit.notifyObservers();
		for(Projectile pjt:player.getProjectiles())
			pjt.notifyObservers();
		for(Projectile pjt:playerAI.getProjectiles())
			pjt.notifyObservers();
	}
	
	/**
	 * Updates the elements positions according to their movement speed
	 */
	public void updateElements(){
		for(Element elem: this.elements){
			elem.setX(elem.getX()+elem.getVelX());
			elem.setY(elem.getY()+elem.getVelY());
			if(elem.isGravity())
				elem.setVelY(elem.getVelY()- Game.GRAVITY);
		}
	}
	
	/**
	 * Updates the Queue where the units are created
	 */
	public void updateUnitsQueue(){
		this.player.updateQueue();
		this.playerAI.updateQueue();
	}

	public void checkCollisions(){
		ArrayList<Collision> cols = new ArrayList<Collision>();
		ArrayList<Projectile> toDispose = new ArrayList<Projectile>();
		for(Projectile pjt: player.getProjectiles()){
			if(ground.isContained(pjt.getCollisionPoint())){
				toDispose.add(pjt);
			}else{
				for(Unit unit:playerAI.getUnits()){
					if(unit.getElement().isContained(pjt.getCollisionPoint())){
						cols.add(new Collision(pjt, unit));
						//System.out.println("Added1");
					}
				}
			}
			if(playerAI.getBase().getElement().isContained(pjt.getCollisionPoint())){
				cols.add(new Collision(pjt, playerAI.getBase()));
			}
		}
		for(Projectile pjt: playerAI.getProjectiles()){
			if(ground.isContained(pjt.getCollisionPoint())){
				toDispose.add(pjt);
			}else{
				for(Unit unit:player.getUnits()){
					if(unit.getElement().isContained(pjt.getCollisionPoint().x, pjt.getCollisionPoint().y)){
						cols.add(new Collision(pjt, unit));
						//System.out.println("Added2");
					}
				}
			}
			if(player.getBase().getElement().isContained(pjt.getCollisionPoint())){
				cols.add(new Collision(pjt, player.getBase()));
			}
		}
		for(Collision col:cols){
			col.Collide();
		}
		for(Projectile pjt:toDispose){
			disposeProjectile(pjt);
		}
	}
	
	public void updateAttackObjectives(){
		for(Unit unit:player.getUnits()){
			unit.updateAttackObjective();
		}
		for(Unit unit:playerAI.getUnits()){
			unit.updateAttackObjective();
		}
		if(player.getTower() != null){
			//System.out.println("Updateo Player");
			player.getTower().updateAttackObjective();
		}	
		if(playerAI.getTower() != null){
			//System.out.println("Updateo playerAI");
			playerAI.getTower().updateAttackObjective();	
		}	
	}

	public void disposeProjectile(Projectile pjt){
		pjt.notifyDelete();
		this.elements.remove(pjt.getElement());
		this.player.getProjectiles().remove(pjt);
		this.playerAI.getProjectiles().remove(pjt);
	}

	public void disposeTower(Tower tower){
		tower.notifyDelete();
		if (tower.getPlayer() == this.player){
			this.elements.remove(player.getTower());
			this.player.disposeTower();
		}
		else{
			this.playerAI.disposeTower();
			this.elements.remove(playerAI.getTower());
		}	

	}

	public void killUnits(){
		for(Unit unit:this.unitsToKill){
			this.killUnit(unit);
		}
		unitsToKill.clear();
	}

	public void killUnit(Unit thisUnit){
		this.elements.remove(thisUnit.getElement());
		if(thisUnit.getSide()==Side.LEFT){
			playerAI.addGold(thisUnit.getGold());
			playerAI.addExp(thisUnit.getExp());
			player.getUnits().remove(thisUnit);
		}
		else{
			player.addGold(thisUnit.getGold());
			player.addExp(thisUnit.getExp());
			playerAI.getUnits().remove(thisUnit);	
		}
		thisUnit.notifyDelete();
	}
	
	/**
	 * Given an attacker returns the nearest enemy attackable 
	 */
	public Attackable isInRange(CanAttack attacker){
		int range = attacker.getAttackRange();
		if (attacker.getSide() == Side.LEFT){
			for(Unit unit:playerAI.getUnits()){
				float distance = unit.getX() - attacker.getWidth() - attacker.getX();
				if ( distance < range && distance > 0 && ((attacker.canAttackFlying()) || (!unit.doesFly()))){
					return unit;
				}
			}
			if (((playerAI.getBase().getX() - attacker.getWidth() - attacker.getX()) < range)){
				return playerAI.getBase();
			}
		}
		else{
			for(Unit unit:player.getUnits()){
				float distance = attacker.getX() - unit.getX() - unit.getWidth();
				if (distance < range && distance > 0 && (attacker.canAttackFlying() || (!unit.doesFly()))) {
					return unit;
				}
			}

			if (((attacker.getX() - player.getBase().getX() - player.getBase().getWidth()) < range)) {
				return player.getBase();
			}
		}
		return null;
	}
	
	public boolean canAdvance(Unit thisUnit){
		if (!measureDistance(player, thisUnit, MINDISTANCE))
			return false;
		if (!measureDistance(playerAI, thisUnit, MINDISTANCE))
			return false;
		return true;
	}
	
	/**
	 * Measures distance between units in the same terrain
	 */
	private boolean measureDistance(Player player, Unit thisUnit, double minDistance){
		double distance;
		for(Unit unit: player.getUnits()){
			if(thisUnit.doesFly() == unit.doesFly()){
				if (thisUnit.getSide() == Side.RIGHT)
					distance = thisUnit.getX() - unit.getX() - unit.getWidth();
				else
					distance = unit.getX() - thisUnit.getX() - thisUnit.getWidth();

				if (!unit.equals(thisUnit) && (distance > 0) && (distance < minDistance))
				{
					return false;
				}
			}
		}
		if (thisUnit.getSide() == Side.RIGHT)
			distance = thisUnit.getX() - player.getBase().getX() - player.getBase().getWidth();
		else
			distance = player.getBase().getX() - thisUnit.getX() - thisUnit.getWidth();
		if ((distance > 0) && (distance < minDistance))
		{
			return false;
		}
		return true;
	}

	public void updateUnitsSpeed(){
		for(Unit unit:player.getUnits())
			unit.updateSpeed();
		for(Unit unit:playerAI.getUnits())
			unit.updateSpeed();
	}

	public Player getPlayer(){
		return player;
	}

	public Player getPlayerAI(){
		return playerAI;
	}

	public ArrayList<Element> getElements(){
		return elements;
	}

	public void endGame(){
		Game.gameState = GameState.MENU;
		Game.setOnGame(false);
	}

	public static void disposeWM(){
		instance = null;
	}

	public static void setInstance(WorldManager WM){
		instance = WM;
	}

	public String toString(){
		String str = player.toString() + " " +playerAI.toString() + " " + elements.toString();
		return str;
	}


	public void addToKillList(Unit unit) {
		this.unitsToKill.add(unit);
	}
}

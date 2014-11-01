package ar.edu.itba.game;


import java.io.Serializable;
import java.util.ArrayList;

import Units.Unit;

public class WorldManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4035477768723084005L;
	public static double MINDISTANCE = 25;
	public static int INITIAL_GOLD = 300;
	
	private Player player;
	private Player AI;
	private ArrayList<Element> elements = new ArrayList<Element>();
	private Element ground;
	
	private static WorldManager instance = null;

	private WorldManager() {
		player = new Player(Side.LEFT);
		AI = new Player(Side.RIGHT);
		ground = new Element(-Game.WIDTH * 10, 0, Game.WIDTH * 21,  Game.GROUND_HEIGHT);
	}
	
	public static WorldManager getInstance() {
      if(instance == null) {
         instance = new WorldManager();
      }
      return instance;
   }
	
	public void notifyObservers(){
		for(Unit unit:player.getUnits())
			unit.notifyObservers();
		for(Unit unit:AI.getUnits())
			unit.notifyObservers();
		for(Projectile pjt:player.getProjectiles())
			pjt.notifyObservers();
		for(Projectile pjt:AI.getProjectiles())
			pjt.notifyObservers();
		if (this.player.getTower() != null)
			player.getTower().notifyObservers();
	}
	
	public void updateElements(){
		for(Element elem: this.elements){
			elem.setX(elem.getX()+elem.getVelX());
			elem.setY(elem.getY()+elem.getVelY());
			if(elem.isGravity())
				elem.setVelY(elem.getVelY()- Game.GRAVITY);
		}
	}
	
	public void checkCollisions(){
		ArrayList<Collision> cols = new ArrayList<Collision>();
		ArrayList<Projectile> toDispose = new ArrayList<Projectile>();
		for(Projectile pjt: player.getProjectiles()){
			if(ground.isContained(pjt.getCollisionPoint())){
				toDispose.add(pjt);
			}else{
				for(Unit unit:AI.getUnits()){
					if(unit.getElement().isContained(pjt.getCollisionPoint())){
						cols.add(new Collision(pjt, unit));
						//System.out.println("Added1");
					}
				}
			}
			if(AI.getBase().getElement().isContained(pjt.getCollisionPoint())){
				cols.add(new Collision(pjt, AI.getBase()));
			}
		}
		for(Projectile pjt: AI.getProjectiles()){
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
		for(Unit unit:AI.getUnits()){
			unit.updateAttackObjective();
		}
		if(player.getTower() != null){
			System.out.println("Updateo Player");
			player.getTower().updateAttackObjective();
		}	
		if(AI.getTower() != null){
			System.out.println("Updateo AI");
			AI.getTower().updateAttackObjective();	
		}	
	}
	
	public void disposeProjectile(Projectile pjt){
		pjt.notifyDelete();
		this.elements.remove(pjt.getElement());
		this.player.getProjectiles().remove(pjt);
		this.AI.getProjectiles().remove(pjt);
	}
	
	public void disposeTower(Tower tower){
		tower.notifyDelete();
		if (tower.getPlayer() == this.player){
			this.elements.remove(player.getTower());
			this.player.setTower(null);
		}
		else{
			this.AI.setTower(null);
			this.elements.remove(AI.getTower());
		}	
		
	}
	
	public void killUnit(Unit thisUnit){
		//thisUnit.removeElement();
		
		
		this.elements.remove(thisUnit.getElement());
		if(thisUnit.getSide()==Side.LEFT){
			AI.addGold(thisUnit.getGold());
			AI.addExp(thisUnit.getExp());
			player.getUnits().remove(thisUnit);
			System.out.println("AI gold is: " + AI.getGold());
//			for(Unit unit: player.getUnits())
//				if (unit.equals(thisUnit))
//					player.getUnits().iterator().remove();
		}
		else{
			player.addGold(thisUnit.getGold());
			player.addExp(thisUnit.getExp());
			AI.getUnits().remove(thisUnit);
			System.out.println("player gold is: " + player.getGold());
//			for(Unit unit: AI.getUnits())
//				if (unit.equals(thisUnit))
//					AI.getUnits().iterator().remove();	
		}
		thisUnit.notifyDelete();
	}
	
	public Attackable isInRange(CanAttack attacker){
		//System.out.println(attacker.getClass().getSimpleName() + " " + attacker.getSide() + " attack range: " + attacker.getAttackRange());
		int range = attacker.getAttackRange();
		if (attacker.getSide() == Side.LEFT){
			for(Unit unit:AI.getUnits()){
				if (((unit.getX() - attacker.getWidth() - attacker.getX()) < range) &&
						((attacker.canAttackFlying()) || (!unit.doesFly())))
					return unit;
			}
			if (((AI.getBase().getX() - attacker.getWidth() - attacker.getX()) < range)){
				return AI.getBase();
			}
		}
		else{
			for(Unit unit:player.getUnits()){
				if (((attacker.getX() - unit.getX() - unit.getWidth()) < range)) {
					//System.out.println("vuela? " + unit.doesFly() + " " + attacker.canAttackFlying() + " objetivo: " + unit.getClass().getSimpleName());
					//System.out.println("esta en rango, atackker:" + attacker.getClass().getSimpleName());
						if(attacker.canAttackFlying() || (!unit.doesFly())){
								//System.out.println("esta atacando, attacker: " + attacker.getClass().getSimpleName());
								return unit;
					}
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
	   if (!measureDistance(AI, thisUnit, MINDISTANCE))
		   return false;
	   return true;
   }
	
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
		for(Unit unit:AI.getUnits())
			unit.updateSpeed();
	}

   public Player getPlayer(){
	   return player;
   }
   
   public Player getAI(){
	   return AI;
   }
   
   public ArrayList<Element> getElements(){
	   return elements;
   }
   
   public void endGame(){
   }
   
   public static void disposeWM(){
	   instance = null;
   }
   
   public static void setInstance(WorldManager WM){
	   instance = WM;
   }
   
   public String toString(){
	   String str = player.toString() + " " +AI.toString() + " " + elements.toString();
	   return str;
   }
   
   public void reAssignObservers(){
	   for(Unit unit:player.getUnits()){
		   unit.reAssignObserver();
	   }
	   for(Unit unit:AI.getUnits()){
		   unit.reAssignObserver();
	   }
	   for(Projectile pjt:player.getProjectiles()){
		   pjt.reAssignObserver();
	   }
	   for(Projectile pjt:AI.getProjectiles()){
		   pjt.reAssignObserver();
	   }
	   player.getBase().reAssignObserver();
	   AI.getBase().reAssignObserver();
	   if(player.getTower()!=null)
		   player.getTower().reAssignObserver();
	   if(AI.getTower()!=null)
		   AI.getTower().reAssignObserver();
	   
   }
}

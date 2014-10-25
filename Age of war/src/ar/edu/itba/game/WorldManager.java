package ar.edu.itba.game;

import java.util.ArrayList;

enum Side{LEFT, RIGHT};

public class WorldManager {
	
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
					if(unit.getElement().isContained(pjt.getCollisionPoint().x, pjt.getCollisionPoint().y)){
						cols.add(new Collision(pjt, unit));
						//System.out.println("Added1");
					}
				}
			}
		}
		for(Projectile pjt: AI.getProjectiles())
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
		for(Collision col:cols){
			col.Collide();
		}
		for(Projectile pjt:toDispose){
			disposeProjectile(pjt);
		}
	}
	
	public void updateUnitsObjectives(){
		for(Unit unit:player.getUnits()){
			unit.updateAttackObjective();
		}
		for(Unit unit:AI.getUnits()){
			unit.updateAttackObjective();
		}			
	}
	
	public void disposeProjectile(Projectile pjt){
		pjt.notifyDelete();
		this.elements.remove(pjt.getElement());
		this.player.getProjectiles().remove(pjt);
		this.AI.getProjectiles().remove(pjt);
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
		for(Unit unit: player.getUnits()){
			   double distance;
			   if (thisUnit.getSide() == Side.RIGHT)
				   distance = thisUnit.getX() - unit.getX() - unit.getWidth();
			   else
				   distance = unit.getX() - thisUnit.getX() - thisUnit.getWidth();
			   if (thisUnit.getSide() == Side.RIGHT)
				   distance = thisUnit.getX() - unit.getX() - unit.getWidth();
			   if (!unit.equals(thisUnit) && (distance > 0) && (distance < minDistance))
			   {
				   return false;
			   }
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
   
   public void EndGame(){
   }
}

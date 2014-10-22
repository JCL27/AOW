package ar.edu.itba.game;

import java.util.ArrayList;
import java.util.Observable;

enum Side{LEFT, RIGHT};

public class WorldManager {
	
	public static double MINDISTANCE = 25;
	
	private Player player;
	private Player AI;
	private ArrayList<Element> elements = new ArrayList<Element>();
	private ArrayList<Observable> observers = new ArrayList<Observable>();
	private Element ground;
	
	private static WorldManager instance = null;

	private WorldManager() {
		player = new Player(this);
		AI = new Player(this);
		ground = new Element(0, 0, Game.GROUND_HEIGHT, Game.WIDTH * Game.SCALE);
		//llamar a UIManager
		this.setUnitsStats();
	}
	
	private void setUnitsStats() {
		//MeleeUnitStats
		MeleeUnit.getATTACK_RANGE().add(300);
		MeleeUnit.getATTACK_RANGE().add(350);
		MeleeUnit.getATTACK_RANGE().add(400);
		
		MeleeUnit.getATTACK_SPEED().add(4.2);
		MeleeUnit.getATTACK_SPEED().add(4.5);
		MeleeUnit.getATTACK_SPEED().add(4.8);
		MeleeUnit.getATTACK_SPEED().add(5.2);
		
		MeleeUnit.getMAX_HP().add(400);
		MeleeUnit.getMAX_HP().add(500);
		MeleeUnit.getMAX_HP().add(600);
		
		MeleeUnit.getDAMAGE().add(60);
		MeleeUnit.getDAMAGE().add(80);
		MeleeUnit.getDAMAGE().add(100);
		MeleeUnit.getDAMAGE().add(120);
		
		MeleeUnit.getMOVEMENT_SPEED().add(1);
		MeleeUnit.getMOVEMENT_SPEED().add(2);
		MeleeUnit.getMOVEMENT_SPEED().add(3);
		
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
						System.out.println("Added1");
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
						System.out.println("Added2");
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
	}
	
	public Attackable isInRange(CanAttack attacker){
		int range = attacker.getAttackRange();
		if (attacker.getSide() == Side.LEFT){
			for(Unit unit:AI.getUnits()){
				if ((unit.getX() - attacker.getWidth() - attacker.getX()) < range)
					return unit;
			}
		}
		else{
			for(Unit unit:player.getUnits()){
				if ((attacker.getX() - unit.getX() - unit.getWidth()) < range)
					return unit;
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
   
   public void updateObservers(){
	   
   }
}

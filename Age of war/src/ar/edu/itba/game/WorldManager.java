package ar.edu.itba.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


enum Side{LEFT, RIGHT};

public class WorldManager {
	
	public static double MINDISTANCE = 25;
	
	private Player player;
	private Player AI;
	private ArrayList<Element> elements = new ArrayList<Element>();	
	private static WorldManager instance = null;
   
	private WorldManager() {
		player = new Player(this);
		AI = new Player(this);
		
		elements.add(new Element(new Texture(Gdx.files.classpath("resources/green button.png")), 500, 500, 0, 0, 6, false));
	}
	
	public static WorldManager getInstance() {
      if(instance == null) {
         instance = new WorldManager();
      }
      return instance;
   }

	public void checkCollisions(){
		ArrayList<Collision> cols = new ArrayList<Collision>();
		for(Projectile pjt: player.getProjectiles()){
			for(Unit unit:AI.getUnits()){
				if(unit.getElement().isContained(pjt.getCollisionPoint().x, pjt.getCollisionPoint().y)){
					cols.add(new Collision(pjt, unit));
					System.out.println("Added1");
				}
			}
		}
		for(Projectile pjt: AI.getProjectiles())
			for(Unit unit:player.getUnits()){
				if(unit.getElement().isContained(pjt.getCollisionPoint().x, pjt.getCollisionPoint().y)){
					cols.add(new Collision(pjt, unit));
					System.out.println("Added2");
				}
			}
		for(Collision col:cols){
			col.Collide();
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
		this.elements.remove(pjt.getElement());
		this.player.getProjectiles().remove(pjt);
		this.AI.getProjectiles().remove(pjt);
	}
	
	public void killUnit(Unit thisUnit){
		//thisUnit.removeElement();
		this.elements.remove(thisUnit.getElement());
		
		if(thisUnit.getSide()==Side.LEFT){
			player.getUnits().remove(thisUnit);
//			for(Unit unit: player.getUnits())
//				if (unit.equals(thisUnit))
//					player.getUnits().iterator().remove();
		}
		else{
			AI.getUnits().remove(thisUnit);
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
				   distance = thisUnit.getX() - unit.getX() - unit.getWidth()/unit.getScale();
			   else
				   distance = unit.getX() - thisUnit.getX() - thisUnit.getWidth()/thisUnit.getScale();
			   if (thisUnit.getSide() == Side.RIGHT)
				   distance = thisUnit.getX() - unit.getX() - unit.getWidth()/unit.getScale();
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
   
}

package ar.edu.itba.game;

import java.util.ArrayList;

import Buttons.*;

enum Side{LEFT, RIGHT};

public class WorldManager {
	
	public static double MINDISTANCE = 25;
	
	private Player player;
	private Player AI;
	private ArrayList<Element> elements = new ArrayList<Element>();	
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private static WorldManager instance = null;
   
	public ArrayList<Button> getButtons() {
		return buttons;
	}

	private WorldManager() {
		player = new Player(this);
		AI = new Player(this);
		this.setUI();
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

	private void setUI(){
		buttons.add(new Button(Textures.BUTTON, 500, 500, 6));
		buttons.add(new UpgradeMeleeUnitDamage(Textures.BUTTON, 700, 550, 10));
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

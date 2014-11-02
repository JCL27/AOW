package ar.edu.itba.game;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Units.Unit;

public class WorldManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4035477768723084005L;
	public static double MINDISTANCE = 25;
	public static int INITIAL_GOLD = 2000;
	private static boolean playerCreatingUnit = false;
	private static boolean AICreatingUnit = false;
	private static int playerUnitCreationTime = 0;
	private static int AIUnitCreationTime = 0;
	
	private Player player;
	private Player playerAI;
	private ArrayList<Element> elements = new ArrayList<Element>();
	private Element ground;
	
	private static WorldManager instance = null;

	private WorldManager() {
		player = new Player(Side.LEFT);
		playerAI = new Player(Side.RIGHT);
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
		for(Unit unit:playerAI.getUnits())
			unit.notifyObservers();
		for(Projectile pjt:player.getProjectiles())
			pjt.notifyObservers();
		for(Projectile pjt:playerAI.getProjectiles())
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
	
	/*public void updateUnitsQueue(){
		if(!this.player.getUnitsQueue().isEmpty()){
			Unit unit = player.getUnitsQueue().get(0);
			System.out.println("En Player");
			if(unit.getCreationTime() == 0){
				this.player.getUnits().add(unit);
				this.player.getUnitsQueue().remove(0);
			}
			else{
				unit.reduceCreationTime();
				//System.out.println(unit.getCreationTime());
			}
		}
		if(!this.AI.getUnitsQueue().isEmpty()){
			System.out.println("En AI");
			Unit unit = AI.getUnitsQueue().get(0);
			if(unit.getCreationTime() == 0){
				this.AI.getUnits().add(unit);
				this.AI.getUnitsQueue().remove(0);
			}
			else
				unit.reduceCreationTime();
		}
	}*/
	
	public void updateUnitsQueue(){
		//Player[] players = {this.player, this.playerAI};
		if(!this.player.getUnitsQueue().isEmpty()){
			System.out.println("Hay algo en cola");
			Class unitClass = this.player.getUnitsQueue().get(0);
			if(this.playerCreatingUnit && this.playerUnitCreationTime == 0){
				this.player.createUnit(unitClass);
				this.player.getUnitsQueue().remove(0);
				this.playerCreatingUnit = false;
			}
			else if(this.playerCreatingUnit){
				this.playerUnitCreationTime-- ;
			}
			else{
				try {
					this.playerUnitCreationTime = (int) unitClass.getMethod("getCreationTime", null).invoke(null, null);
					this.playerCreatingUnit = true;
				} catch (IllegalAccessException | IllegalArgumentException| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(this.playerUnitCreationTime);
			}
		}
		
		if(!this.playerAI.getUnitsQueue().isEmpty()){
			Class unitClass = this.playerAI.getUnitsQueue().get(0);
			if(this.AICreatingUnit && this.AIUnitCreationTime == 0){
				this.playerAI.createUnit(unitClass);
				this.playerAI.getUnitsQueue().remove(0);
				this.AICreatingUnit = false;
			}
			else if(this.AICreatingUnit){
				this.AIUnitCreationTime-- ;
			}
			else{
				try {
					this.AIUnitCreationTime = (int) unitClass.getMethod("getCreationTime", null).invoke(null, null);
					this.AICreatingUnit = true;
				} catch (IllegalAccessException | IllegalArgumentException| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
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
			System.out.println("Updateo Player");
			player.getTower().updateAttackObjective();
		}	
		if(playerAI.getTower() != null){
			System.out.println("Updateo playerAI");
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
	
	public void killUnit(Unit thisUnit){
		//thisUnit.removeElement();
		
		
		this.elements.remove(thisUnit.getElement());
		if(thisUnit.getSide()==Side.LEFT){
			playerAI.addGold(thisUnit.getGold());
			playerAI.addExp(thisUnit.getExp());
			player.getUnits().remove(thisUnit);
			System.out.println("playerAI gold is: " + playerAI.getGold());
//			for(Unit unit: player.getUnits())
//				if (unit.equals(thisUnit))
//					player.getUnits().iterator().remove();
		}
		else{
			player.addGold(thisUnit.getGold());
			player.addExp(thisUnit.getExp());
			playerAI.getUnits().remove(thisUnit);
			System.out.println("player gold is: " + player.getGold());
//			for(Unit unit: playerAI.getUnits())
//				if (unit.equals(thisUnit))
//					playerAI.getUnits().iterator().remove();	
		}
		thisUnit.notifyDelete();
	}
	
	public Attackable isInRange(CanAttack attacker){
		//System.out.println(attacker.getClass().getSimpleName() + " " + attacker.getSide() + " attack range: " + attacker.getAttackRange());
		int range = attacker.getAttackRange();
		if (attacker.getSide() == Side.LEFT){
			for(Unit unit:playerAI.getUnits()){
				if (((unit.getX() - attacker.getWidth() - attacker.getX()) < range) &&
						((attacker.canAttackFlying()) || (!unit.doesFly())))
					return unit;
			}
			if (((playerAI.getBase().getX() - attacker.getWidth() - attacker.getX()) < range)){
				return playerAI.getBase();
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
	   if (!measureDistance(playerAI, thisUnit, MINDISTANCE))
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
		for(Unit unit:playerAI.getUnits())
			unit.updateSpeed();
	}

   public Player getPlayer(){
	   return player;
   }
   
   public Player getplayerAI(){
	   return playerAI;
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
	   String str = player.toString() + " " +playerAI.toString() + " " + elements.toString();
	   return str;
   }
   
   public void reAssignObservers(){
	   for(Unit unit:player.getUnits()){
		   unit.reAssignObserver();
	   }
	   for(Unit unit:playerAI.getUnits()){
		   unit.reAssignObserver();
	   }
	   for(Projectile pjt:player.getProjectiles()){
		   pjt.reAssignObserver();
	   }
	   for(Projectile pjt:playerAI.getProjectiles()){
		   pjt.reAssignObserver();
	   }
	   player.getBase().reAssignObserver();
	   playerAI.getBase().reAssignObserver();
	   if(player.getTower()!=null)
		   player.getTower().reAssignObserver();
	   if(playerAI.getTower()!=null)
		   playerAI.getTower().reAssignObserver();
	   
   }
}

package Units;

import java.util.Observable;

import ar.edu.itba.game.Attackable;
import ar.edu.itba.game.CanAttack;
import ar.edu.itba.game.Element;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Projectile;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.Type;
import ar.edu.itba.game.WorldManager;
import Observers.UnitObserver;
import exceptions.DeadUnitException;


public abstract class Unit extends Observable implements CanAttack, Attackable{
	
	protected Type type;
	protected boolean attackFlying;
	protected static int playerUnitLevel = 0;
	protected static int AIUnitLevel = 0;
	
	protected int hp;
	protected int maxHp;
	protected double attackSpeed;
	protected int attackRange;
	protected int movementSpeed;
	protected int damage;
	protected Player player;
	protected Side dir;
	protected Attackable objective;
	protected UnitObserver observer;
	
	protected int cooldown;
	protected Element element;
	
	protected int bounty;
	protected int exp;
	protected int cost;
	
	public int getGold() {
		return this.bounty;
	}

	public void setGold(int gold) {
		this.bounty = gold;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getExp() {
		return this.exp;
	}

	public int getCost() {
		return this.cost;
	}
	public boolean doesFly(){
		if(this.type == Type.FLYING){
			return true;
		}
		return false;
	}
	
	public boolean canAttackFlying(){
		return this.attackFlying;
	}
	
	public Type getType(){
		return this.type;
	}

	public void attack(Attackable objective){
		if(this.cooldown == 0){
			float velX;
			double velY = Math.sqrt(Math.abs(this.getElement().getMiddleX() - objective.getElement().getMiddleX()) *
					Game.GRAVITY / 2);
			if(this.getSide()==Side.RIGHT)
				velX = (float)-velY;
			else
				velX = (float)velY;
			this.player.getProjectiles().add(new Projectile(this.getElement().getMiddleX(),
					this.getElement().getMiddleY(), velX , (float)velY , true, this.damage));
			
			//System.out.println("attack!");

			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;
		}
	}
	
	
	public Unit(){
		
	}
	
	public Unit(int maxHp, double attackSpeed, int attackRange, int movementSpeed, int damage, Element element, Side dir, Player player){
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackSpeed = attackSpeed;
		this.attackRange = attackRange;
		this.movementSpeed = movementSpeed;
		this.damage = damage;
		this.element = element;
		WorldManager.getInstance().getElements().add(element);
		this.dir = dir;
		this.objective = null;
		this.cooldown = 0;
		this.player = player;
	}
	
	
	@Override
	public void receiveDamage(int damage) throws DeadUnitException{
		// TODO Auto-generated method stub
		this.hp-= damage;
		//System.out.println(this.hp + " " + this.getSide());
		if(this.hp <= 0){
			throw new DeadUnitException(this);
		}
	}
	
	public void removeElement(){
		this.element = null;
	}
	
	public void updateAttackObjective(){
		if (this.objective == null || !WorldManager.getInstance().getElements().contains(this.objective.getElement())){
			this.objective = WorldManager.getInstance().isInRange(this);
		}
		if(this.objective != null){
			//System.out.println("objective set " + this.getSide());
			this.attack(this.objective);
		}
	}
	
	public void updateSpeed(){
		if(WorldManager.getInstance().canAdvance(this)){
			this.element.setVelX(this.movementSpeed);
		}
		else{
			this.element.setVelX(0);
		}
	}
	
	public Element getElement(){
		return this.element;
	}

	public Side getSide(){
		return this.dir;
	}
	
	public float getX(){
		return this.element.getX();
	}

	public float getY(){
		return this.element.getY();
	}
	
	public int getWidth(){
		return this.element.getWidth();
	}
	
	public int getHeight(){
		return this.element.getHeight();
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public static void playerLevelUp(){
		playerUnitLevel++;
	}
	
	public static void AILevelUp(){
		AIUnitLevel++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attackRange;
		long temp;
		temp = Double.doubleToLongBits(attackSpeed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + damage;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + hp;
		result = prime * result + maxHp;
		result = prime * result + movementSpeed;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		if (attackRange != other.attackRange)
			return false;
		if (Double.doubleToLongBits(attackSpeed) != Double
				.doubleToLongBits(other.attackSpeed))
			return false;
		if (damage != other.damage)
			return false;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (hp != other.hp)
			return false;
		if (maxHp != other.maxHp)
			return false;
		if (movementSpeed != other.movementSpeed)
			return false;
		return true;
	}

	@Override
	public int getAttackRange() {
		// TODO Auto-generated method stub
		return this.attackRange;
	}

	public void notifyObservers(){
		this.observer.update(null, null);
	}
	
	public void notifyDelete() {
		this.observer.dispose();	
	}
}

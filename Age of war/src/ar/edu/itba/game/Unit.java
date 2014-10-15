package ar.edu.itba.game;

import exceptions.DeadUnitException;

public abstract class Unit implements CanAttack, Attackable{
	
	protected int hp;
	protected int maxHp;
	protected double attackSpeed;
	protected int attackRange;
	protected int movementSpeed;
	protected int damage;
	protected Player player;
	protected Side dir;
	protected Attackable objective;
	
	protected int cooldown;
	protected Element element;
	
	public void attack(Attackable objective){
		if(this.cooldown == 0){
			double velX;
			double velY = Math.sqrt(Math.abs(this.getElement().getMiddleX() - objective.getElement().getMiddleX()) * 0.1 / 2);
			if(this.getSide()==Side.RIGHT)
				velX = -velY;
			else
				velX = velY;
			this.player.getProjectiles().add(new Projectile(this.getElement().getMiddleX(), this.getElement().getMiddleY(), velX , velY , true, this.damage));
			
			System.out.println("attack!");

			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;
		}
			
	}
	
	@Override
	public void receiveDamage(int damage) throws DeadUnitException{
		// TODO Auto-generated method stub
		this.hp-= damage;
		System.out.println(this.hp + " " + this.getSide());
		if(this.hp <= 0){
			throw new DeadUnitException(this);
		}
	}
	
	public Unit(){
		this.maxHp = 500;
		this.hp = 500;
		this.attackSpeed = 1.0;
		this.attackRange = 300;
		this.movementSpeed = 100;
		this.damage = 50;
		//this.element = WorldManager.getInstance().newElement(new Texture(Gdx.files.classpath("resources/verde.png")));
		this.dir = 	Side.LEFT;
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
	
	public int getScale(){
		return this.element.getScale();
	}
	
	public Side getSide(){
		return this.dir;
	}
	
	public double getX(){
		return this.element.getX();
	}

	public double getY(){
		return this.element.getY();
	}
	
	public double getWidth(){
		return this.element.getWidth();
	}
	
	public double getHeight(){
		return this.element.getHeight();
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
	
}

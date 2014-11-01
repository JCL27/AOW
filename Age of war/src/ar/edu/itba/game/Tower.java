package ar.edu.itba.game;

import java.util.Observable;

import Observers.TowerObserver;
import Observers.UnitObserver;

public abstract class Tower extends Observable implements CanAttack{
	
	protected Element element;
	protected int damage;
	protected int cost;
	protected int cooldown;
	protected Side side;
	protected float attackSpeed;
	protected int attackRange;
	protected Attackable objective;
	protected Player player;
	protected boolean attackFlying;
	protected TowerObserver observer;
	
	public void updateAttackObjective(){
		if (this.objective == null || !WorldManager.getInstance().getElements().contains(this.objective.getElement())){
			this.objective = WorldManager.getInstance().isInRange(this);
		}
		if(this.objective != null){
			this.attack(this.objective);
		}
		System.out.println("Torre: " + this.player + " Objetivo: " + this.objective);
	}
	public void notifyObservers(){
		this.observer.update(null, null);
	}
	
	public void Sell(){
		this.player.addGold(this.cost/2);
		WorldManager.getInstance().disposeTower(this);
	}
	
	public Element getElement(){
		return this.element;
	}


	@Override
	public void attack(Attackable objective) {
		// TODO Auto-generated method stub
		System.out.println("Entre a attacking");
		if(this.cooldown == 0){
			System.out.println("Esta sin cooldown");
			float velX = 0;
			float velY = 0;
			float Yf = objective.getElement().getMiddleY();
			float Xf = objective.getElement().getMiddleX();
			float X = this.getElement().getMiddleX();
			float Y = this.getElement().getMiddleY();
			if(objective.doesFly()){
				System.out.println("Objetivo es vuela");
				float t = 0;
				float dist = Xf - X;
				float height = Yf - Y;
				velY = (float) Math.sqrt(2 * Game.GRAVITY * (Yf - Y));
				t = (float) ((-velY + Math.sqrt(Math.abs(velY*velY-4*height*Game.GRAVITY/2)))/Game.GRAVITY);
				velX = - dist / t;
				System.out.println(velY + " " + velX );
				//float vel = (float) Math.sqrt((velX*velX+velY*velY));
				
				
			}else{
				System.out.println("Objetivo es terrestre");
				velY = 0;
				velX = (float) Math.sqrt(-Game.GRAVITY * (Xf - X) * (Xf - X) / ((Yf - Y)*2)); 
			//System.out.println("velocidad " + velX);			
			if(this.getSide()==Side.RIGHT)
				velX = -velX;
			
			/*Da new: this.player.getProjectiles().add(new Projectile(this.getElement().getMiddleX(),
					this.getElement().getY()-30, velX , (float)velY , true, this.damage));*/
				
			/* Original: velY = (float) Math.sqrt(Math.abs(this.getElement().getMiddleX() - objective.getElement().getMiddleX()) *
						Game.GRAVITY / 2);
				if(this.getSide()==Side.RIGHT)
					velX = (float)-velY;
				else
					velX = (float)velY;*/
			}
			System.out.println("AntiairCraft pjt vel:" + velX + " " + velY);
			this.player.getProjectiles().add(new Projectile(this.getElement().getMiddleX(),
					this.getElement().getMiddleY(), velX , (float)velY , true, this.damage));
			
			//System.out.println("attack!");

			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;
			System.out.println("Cooldown " + this.cooldown);
		}
		
	}


	@Override
	public int getAttackRange() {
		return this.attackRange;
	}

	@Override
	public Side getSide() {
		return this.side;
	}

	@Override
	public float getX() {
		return this.element.getX();
	}
	
	public float getY(){
		return this.element.getY();
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public int getHeight(){
		return this.element.getHeight();
	}

	@Override
	public int getWidth() {
		return this.element.getWidth();
	}


	@Override
	public boolean canAttackFlying() {
		return this.attackFlying;
	}
	
	public void notifyDelete() {
		this.observer.dispose();	
	}
	
	public void reAssignObserver(){
		this.deleteObservers();
		this.observer = new TowerObserver(this);
		this.addObserver(this.observer);
	}
	
}

package ar.edu.itba.game;

import java.util.Observable;

public class Tower extends Observable implements CanAttack{
	
	protected Element element;
	protected int damage;
	protected int cost;
	protected int cooldown;
	protected Side side;
	protected float attackSpeed;
	protected int attackRange;
	protected Attackable objective;
	protected Player player;
	protected boolean canAttackFlying;
	
	public void updateAttackObjective(){
		if (this.objective == null || !WorldManager.getInstance().getElements().contains(this.objective.getElement())){
			this.objective = WorldManager.getInstance().isInRange(this);
		}
		if(this.objective != null){
			
			//this.attack(this.objective);
		}
	}
	
	
	public void Sell(){
		this.player.addGold(cost/2);
		WorldManager.getInstance().disposeTower(this);
	}
	
	public Element getElement(){
		return this.element;
	}


	@Override
	public void attack(Attackable objective) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	
}

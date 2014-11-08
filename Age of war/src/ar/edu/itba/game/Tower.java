package ar.edu.itba.game;

import java.io.Serializable;

import Observers.TowerObserver;
import exceptions.AlreadyAppliedUpgradeException;

public class Tower implements CanAttack, Serializable{
	
	private static final long serialVersionUID = 6947500221042518381L;
	
	private Element element;
	private int damage;
	private int cost;
	private int cooldown;
	private Side side;
	private float attackSpeed;
	

	private int attackRange;
	private Attackable objective;
	private Player player;
	private boolean attackFlying;
	private transient TowerObserver observer;
	
	private boolean upgradedDamage = false;
	private boolean upgradedAttackRange = false;
	private boolean upgradedAttackSpeed = false;
	
	
	public boolean isUpgradedDamage() {
		return upgradedDamage;
	}
	public boolean isUpgradedAttackRange() {
		return upgradedAttackRange;
	}
	public boolean isUpgradedAttackSpeed() {
		return upgradedAttackSpeed;
	}
	
	public void updateAttackObjective(){
		if (this.objective == null || !WorldManager.getInstance().getElements().contains(this.objective.getElement())){
			this.objective = WorldManager.getInstance().isInRange(this);
		}
		if(this.objective != null){
			this.attack(this.objective);
		}
		//System.out.println("Torre: " + this.player + " Objetivo: " + this.objective);
	}
	
	public void Sell(){
		this.player.addGold(this.cost/2);
		WorldManager.getInstance().disposeTower(this);
	}
	
	public Element getElement(){
		return this.element;
	}
	/////////////////////

	public Tower(Player player, TowerObserver observer){
		this.player = player;
		this.objective = null;
		this.attackFlying = true;
		this.cooldown = 0;
		
		this.damage = GameStats.TOWER_DAMAGE;
		this.cost = GameStats.TOWER_COST;
		this.attackSpeed = GameStats.TOWER_ATTACK_SPEED;
		this.attackRange = GameStats.TOWER_ATTACK_RANGE;
		
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
			this.side = Side.LEFT;
			this.element = new Element(50, GameStats.TOWER_HEIGHT + Game.GROUND_HEIGHT, 200, 100);
		} 
		else{
			this.side = Side.RIGHT;
			this.element = new Element(1000, GameStats.TOWER_HEIGHT + Game.GROUND_HEIGHT, 100, 100);
		}
		
		this.observer = observer;
		
		Upgrades.Upgrades.getInstance().setAvailable("TowerDamageUpgrade", this.player);
		Upgrades.Upgrades.getInstance().setAvailable("TowerAttackSpeedUpgrade", this.player);
		Upgrades.Upgrades.getInstance().setAvailable("TowerAttackRangeUpgrade", this.player);
	}
	///////////////////////////

	@Override
	public void attack(Attackable objective) {
		if(this.cooldown == 0){
			float velX = 0;
			float velY = 0;
			float Yf = objective.getElement().getMiddleY();
			float Xf = objective.getElement().getMiddleX();
			float X = this.getElement().getMiddleX();
			float Y = this.getElement().getMiddleY();
			if(objective.doesFly()){
				float t = 0;
				float dist = Xf - X;
				float height = Yf - Y;
				velY = (float) Math.sqrt(2 * Game.GRAVITY * (Yf - Y));
				t = (float) ((-velY + Math.sqrt(Math.abs(velY*velY-4*height*Game.GRAVITY/2)))/Game.GRAVITY);
				velX = - dist / t;
			}else{
				velY = 0;
				velX = (float) Math.sqrt(-Game.GRAVITY * (Xf - X) * (Xf - X) / ((Yf - Y)*2)); 		
			if(this.getSide()==Side.RIGHT)
				velX = -velX;
			}
			
			this.player.getProjectiles().add(UnitFactory.getInstance().createProjectile(this.getElement().getMiddleX(),
					this.getElement().getMiddleY(), velX , (float)velY , true, this.damage));

			this.cooldown = (int)(1000/this.attackSpeed);		

		}
		else{
			this.cooldown--;

		}
		
	}

	public void upgradeDamage() throws AlreadyAppliedUpgradeException{
		if(!this.upgradedDamage){
			this.upgradedDamage = true;
			this.damage += GameStats.TOWER_DAMAGE_BONUS;
		}else
			throw new AlreadyAppliedUpgradeException();
	}
	
	public void upgradeAttackRange() throws AlreadyAppliedUpgradeException{
		if(!this.upgradedAttackRange){
			this.upgradedAttackRange = true;
			this.attackRange += GameStats.TOWER_ATTACK_RANGE_BONUS;
		}else
			throw new AlreadyAppliedUpgradeException();
	}
	
	public void upgradeAttackSpeed() throws AlreadyAppliedUpgradeException{
		if(!this.upgradedAttackSpeed){
			this.upgradedAttackSpeed = true;
			this.attackSpeed += GameStats.TOWER_ATTACK_SPEED_BONUS;
		}else
			throw new AlreadyAppliedUpgradeException();
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
		this.observer.dispose(this);	
	}
	public void setObserver(TowerObserver towerObserver) {
		this.observer = towerObserver;
		
	}
	
}

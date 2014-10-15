package ar.edu.itba.game;

public class UpgradeStatus {
	private int maxHpIndex;
	private int attackSpeedIndex;
	private int attackRangeIndex;
	private int movementSpeedIndex;
	private int damageIndex;
	private int skillCooldownIndex;
	
	public UpgradeStatus(){
		this.maxHpIndex = 0;
		this.attackRangeIndex = 0;
		this.attackSpeedIndex = 0;
		this.movementSpeedIndex = 0;
		this.damageIndex = 0;
		this.skillCooldownIndex = 0;
	}
	
	public void upgradeMaxHP(){
		this.maxHpIndex++;
	}
	
	public void upgradeAttackRange(){
		this.attackRangeIndex++;
	}
	
	public void upgradeAttackSpeed(){
		this.attackSpeedIndex++;
	}
	
	public void upgradeMovementSpeed(){
		this.movementSpeedIndex++;
	}
	
	public int getMaxHpIndex() {
		return maxHpIndex;
	}

	public int getAttackSpeedIndex() {
		return attackSpeedIndex;
	}

	public int getAttackRangeIndex() {
		return attackRangeIndex;
	}

	public int getMovementSpeedIndex() {
		return movementSpeedIndex;
	}

	public int getDamageIndex() {
		return damageIndex;
	}

	public int getSkillCooldownIndex() {
		return skillCooldownIndex;
	}

	public void upgradeDamage(){
		this.damageIndex++;
	}
	
	public void upgradeSkillCooldown(){
		this.skillCooldownIndex++;
	}
}

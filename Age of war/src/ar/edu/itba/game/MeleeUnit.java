package ar.edu.itba.game;

public class MeleeUnit extends Unit {
	static int MAX_HP = 500;
	static double ATTACK_SPEED = 5;
	static int ATTACK_RANGE = 600;
	static int MOVEMENT_SPEED = 2;
	static int DAMAGE = 80;
	static Element ELEMENT;
	
	
	public MeleeUnit(Player player){
		this.maxHp = MAX_HP;
		this.hp = MAX_HP;
		this.attackSpeed = ATTACK_SPEED;
		this.attackRange = ATTACK_RANGE;
			
		this.damage = DAMAGE;
		this.player = player;
		this.objective = null;
		this.cooldown = 0;
		if (this.player.equals(WorldManager.getInstance().getPlayer())){
			this.element = new Element(Textures.BLUE_BALL, 100, 0, 3, 0, 6, false);
			this.movementSpeed = MOVEMENT_SPEED;
			this.dir = Side.LEFT;
			System.out.println("instancio player");
		}else{
			this.element = new Element(Textures.RED_BALL, 1000, 0, 3, 0, 6, false);
			this.movementSpeed = MOVEMENT_SPEED * (-1);
			this.dir = Side.RIGHT;
			System.out.println("instancio AI");
		}
		WorldManager.getInstance().getElements().add(this.element);

	}
}

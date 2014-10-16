package ar.edu.itba.game;

public class HealthBar extends Element {
	//Multiplicar por scale converter los scales de Width de la barra
	public static final double SCALE_CONVERTION = 3.0/2.0;
	int maxHp;
	int hp;
	int scale;
	Side dir;
	
	public HealthBar(int maxHp, float unitX, float unitY, int unitWidth, int unitHeight, float velX, float velY, int scale, Side dir) {
		super(Textures.HEALTH_BAR, (int)(unitX -(Textures.HEALTH_BAR.getWidth()/(scale*2) - unitWidth/(scale*2)) + 
				(dir == Side.LEFT ? 0:Textures.HEALTH_BAR.getWidth()/(scale))), unitY + unitHeight/scale, velX, velY, scale, false);
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.scale = scale;
		this.dir = dir; 
		
	}
	
	@Override
	public int getScreenWidth() {
		// TODO Auto-generated method stub
		return (int)(getWidth()/scale) * hp/maxHp * (dir == Side.LEFT ? 1:-1);
	}

	public void reduceHp(int damage){
		this.hp -= damage;
		
	}

	public int getHp() {
		return hp;
	}
	
}

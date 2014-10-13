package ar.edu.itba.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Projectile {
	private Element element;
	
	public double getMiddleX(){
		return this.element.getMiddleX();
	}
	
	public double getMiddleY(){
		return this.element.getMiddleY();
	}
	
	public Projectile(double X, double Y, double velX, double velY, boolean gravity){
		Texture texture = new Texture(Gdx.files.classpath("resources/verde.png"));
		this.element = new Element(texture , (float)(X-texture.getWidth()/24)  , (float)Y, (float)velX, (float)velY, 12 , true);
		WorldManager.getInstance().getElements().add(this.element);
	}

}

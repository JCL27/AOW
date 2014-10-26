package Draws;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Textures {
	public static Texture BLUE_BALL = new Texture(Gdx.files.classpath("resources/ball.png"));
	public static Texture RED_BALL = new Texture(Gdx.files.classpath("resources/redBall.png"));
	public static Texture BUTTON = new Texture(Gdx.files.classpath("resources/green button.png"));
	public static Texture GREEN_BUTTON = new Texture(Gdx.files.classpath("resources/greenBall.png"));
	public static Texture HEALTH_BAR = new Texture(Gdx.files.classpath("resources/Healthbar.png"));
	public static Texture GROUND = new Texture(Gdx.files.classpath("resources/ground.png"));
	public static Texture AIR_UNIT = new Texture(Gdx.files.classpath("resources/unGlobo.png"));
	public static Texture ARROW = new Texture(Gdx.files.classpath("resources/Arrow.png"));
}

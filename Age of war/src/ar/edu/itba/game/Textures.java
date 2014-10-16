package ar.edu.itba.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Textures {
	public static Texture BLUE_BALL = new Texture(Gdx.files.classpath("resources/ball.png"));
	public static Texture RED_BALL = new Texture(Gdx.files.classpath("resources/redBall.png"));
	public static Texture BUTTON = new Texture(Gdx.files.classpath("resources/green button.png"));
	public static Texture GREEN_BUTTON = new Texture(Gdx.files.classpath("resources/verde.png"));
	public static Texture HEALTH_BAR = new Texture(Gdx.files.classpath("resources/Healthbar.png"));
}
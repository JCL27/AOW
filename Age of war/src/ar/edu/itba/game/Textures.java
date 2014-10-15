package ar.edu.itba.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Textures {
	static Texture BLUE_BALL = new Texture(Gdx.files.classpath("resources/ball.png"));
	static Texture RED_BALL = new Texture(Gdx.files.classpath("resources/redBall.png"));
	static Texture BUTTON = new Texture(Gdx.files.classpath("resources/green button.png"));
	static Texture GREEN_BUTTON = new Texture(Gdx.files.classpath("resources/verde.png"));
}

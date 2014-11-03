package ar.edu.itba.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

public class Main{
	
	public static void main(String args[]) {

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.title = Game.TITLE;
		cfg.width = Game.WIDTH * Game.SCALE;
		cfg.height = Game.HEIGHT * Game.SCALE;
		cfg.initialBackgroundColor.add(Color.valueOf("B2FFFF"));
		new LwjglApplication(new Game(), cfg);
	}
	
	
}

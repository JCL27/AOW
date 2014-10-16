package ar.edu.itba.game;

import Buttons.Button;
import UserInterface.MyInputProcessor;
import UserInterface.UIManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Game implements ApplicationListener {
	public static final String TITLE = "Age of wars";
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final int SCALE = 2;	
	
	private OrthographicCamera cam;
	private Box2DDebugRenderer b2dr;
	private World world;
	private SpriteBatch SB;
	private Texture tex;
	private Sprite sp;
	private Label label;
	
	public void create() {
		// TODO Auto-generated method stub
		Texture.setEnforcePotImages(false);
		Gdx.input.setInputProcessor(new MyInputProcessor());
		SB = new SpriteBatch();
		
	//	label = new Label("hola");
		
		b2dr = new Box2DDebugRenderer();
		this.world = new World(new Vector2(0,0),true);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		System.out.println(cam.getPickRay(400, 600));
		
		UIManager.getInstance().setSpriteBatch(SB);
		UIManager.getInstance().updateUI();
	}
	
	public OrthographicCamera getCam(){
		return this.cam;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		WorldManager.getInstance().updateUnitsSpeed();
		WorldManager.getInstance().updateUnitsObjectives();
		WorldManager.getInstance().checkCollisions();
		SB.begin();
		
		
		for(Element elem:WorldManager.getInstance().getElements()){
			SB.draw(elem.getTexture(), (float)elem.getX(),(float) elem.getY(), elem.getScreenWidth(), elem.getScreenHeight(), 0, 0, elem.getWidth(), elem.getHeight(), false, false);
			elem.setX(elem.getX()+elem.getVelX());
			elem.setY(elem.getY()+elem.getVelY());
			if(elem.isGravity())
				elem.setVelY(elem.getVelY()-0.1);
		}
		/*for(Button button:WorldManager.getInstance().getButtons()){
			SB.draw(button.getTexture(), (float)button.getX(),(float) button.getY(), button.getWidth()/button.getScale(), button.getHeight()/button.getScale(), 0, 0, button.getWidth(), button.getHeight(), false, false);
		}*/
		UIManager.getInstance().drawButtons();
		SB.end();
		
		b2dr.render(world, cam.combined);
		
	}
	
	//private void spriteIterator(Texture texture, int Height, int Width, int start, int finish){
	//	;
		
	//}
	
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}

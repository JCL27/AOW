package ar.edu.itba.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Units.AntiaircraftUnit;
import Units.FlyingUnit;
import Units.MeleeUnit;
import Units.RangedUnit;
import UserInterface.MyInputProcessor;
import UserInterface.UIManager;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Game implements ApplicationListener {
	public static final String TITLE = "Age of wars";
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final int SCALE = 2;
	public static final float GRAVITY = 0.1f;
	public static final int GROUND_HEIGHT = 300;
	public static final int FLYING_HEIGHT = 300;
	
	public static GameState gameState = GameState.MENU;
	
	private OrthographicCamera cam;
	private Box2DDebugRenderer b2dr;
	private World world;
	private SpriteBatch SB;
	private int secondCount = 0;
	
	public void create() {

		Texture.setEnforcePotImages(false);
		Gdx.input.setInputProcessor(new MyInputProcessor());
		SB = new SpriteBatch();
		
		b2dr = new Box2DDebugRenderer();
		
		this.world = new World(new Vector2(0,0),true);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);

		UIManager.getInstance().setSpriteBatch(SB);
		WorldManager.getInstance();
		WorldManager.getInstance().getElements().add(WorldManager.getInstance().getPlayer().getBase().getElement());
		WorldManager.getInstance().getElements().add(WorldManager.getInstance().getplayerAI().getBase().getElement());
		
		//System.out.println(WorldManager.getInstance().getElements().contains(WorldManager.getInstance().getPlayer().getBase().getElement()));
		
	}
	
	public OrthographicCamera getCam(){
		return this.cam;
	}
	
	@Override
	public void dispose() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render() {		
		
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);

		WorldManager.getInstance().updateUnitsQueue();
		
		WorldManager.getInstance().notifyObservers();
		
		WorldManager.getInstance().updateUnitsSpeed();
		
		WorldManager.getInstance().updateElements();
		
		WorldManager.getInstance().checkCollisions();
		
		WorldManager.getInstance().updateAttackObjectives();
		
		SB.begin();
		
		UIManager.getInstance().drawTextures();
		UIManager.getInstance().drawButtons();
		UIManager.getInstance().drawObjects();
		
		SB.end();
		
		b2dr.render(world, cam.combined);
		
		AI.getInstance().desitionMaker();

		oneSecondLoop();

	}
	
	public static void saveGame(){
		
		//System.out.println("guarda");
			
		   try {
			   FileOutputStream fileO = new FileOutputStream("WM.ser");
			   ObjectOutputStream objO = new ObjectOutputStream(fileO);
			   objO.writeObject(WorldManager.getInstance());
			   objO.close();
			   fileO.close();
		   } catch (FileNotFoundException e) {
				e.printStackTrace();
		   } catch (IOException e) {
			   	e.printStackTrace();
		   }
		   String csv = "levels.csv";
		   CSVWriter writer;
		   try {
			   String[] levels;
			   ArrayList<String[]> data = new ArrayList<String[]>();
			   writer = new CSVWriter(new FileWriter(csv));
			   levels = AntiaircraftUnit.getLevels();
			   data.add(levels);
			   levels = FlyingUnit.getLevels();
			   data.add(levels);
			   levels = MeleeUnit.getLevels();
			   data.add(levels);
			   levels = RangedUnit.getLevels();
			   data.add(levels);
			   writer.writeAll(data);
			   writer.close();
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		    
		   
		   //System.out.println(WorldManager.getInstance().toString());
	
	}
	
	public static void loadGame(){
		System.out.println("carga");
		//System.out.println(WorldManager.getInstance().toString());
		System.out.println(RangedUnit.getLevels()[0] + " " + RangedUnit.getLevels()[1]);	
		try {
			   WorldManager.disposeWM();
			   FileInputStream fileO = new FileInputStream("WM.ser");
			   ObjectInputStream objO = new ObjectInputStream(fileO);
			   WorldManager.setInstance((WorldManager)objO.readObject());
			   objO.close();
			   fileO.close();
		   } catch (FileNotFoundException e) {
				e.printStackTrace();
		   } catch (IOException e) {
			   	e.printStackTrace();
		   } catch (ClassNotFoundException e){
			   e.printStackTrace();
		   }
		try{
			String csvFilename = "levels.csv";
			CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
			String[] row = null;
			row = csvReader.readNext();
			AntiaircraftUnit.setLevels(row);
			row = csvReader.readNext();
			FlyingUnit.setLevels(row);
			row = csvReader.readNext();
			MeleeUnit.setLevels(row);
			row = csvReader.readNext();
			RangedUnit.setLevels(row);
			csvReader.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(RangedUnit.getLevels()[0] + " " + RangedUnit.getLevels()[1]);
			
		   //System.out.println(WorldManager.getInstance().toString());
		   UIManager.getInstance().clearDraws();
		   UIManager.getInstance().initializeDraws();
		   WorldManager.getInstance().reAssignObservers();
	}
	
	private void oneSecondLoop(){
		if(secondCount--<=0){
			secondCount = (int) (1/Gdx.graphics.getDeltaTime());
			//System.out.println("renders/seg: " + 1/Gdx.graphics.getDeltaTime());
			WorldManager.getInstance().getPlayer().addGold(10);
			WorldManager.getInstance().getplayerAI().addGold(10);
		}
	}
	
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}

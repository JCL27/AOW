package ar.edu.itba.game.backend.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ar.edu.itba.game.backend.units.AntiaircraftUnit;
import ar.edu.itba.game.backend.units.FlyingUnit;
import ar.edu.itba.game.backend.units.MeleeUnit;
import ar.edu.itba.game.backend.units.RangedUnit;
import ar.edu.itba.game.backend.upgrades.Upgrades;
import ar.edu.itba.game.frontend.draws.Textures;
import ar.edu.itba.game.frontend.observers.BaseObserver;
import ar.edu.itba.game.frontend.observers.PlayerObserver;
import ar.edu.itba.game.frontend.observers.ProjectileObserver;
import ar.edu.itba.game.frontend.observers.TowerObserver;
import ar.edu.itba.game.frontend.observers.UnitObserver;
import ar.edu.itba.game.frontend.userinterface.MyInputProcessor;
import ar.edu.itba.game.frontend.userinterface.UIManager;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	private static final int ERROR_DISPLAY_TIME = 200;
	public static GameState gameState = GameState.MENU;

	private OrthographicCamera cam;
	private Box2DDebugRenderer b2dr;
	private World world;
	private SpriteBatch SB;
	private int secondCount = 0;
	private boolean menuDisplayed = false;
	private static boolean firstTimeMenu = true;
	private static boolean onGame = false;
	private static BitmapFont errorFont;
	private static int errorDisplayTime = 140;

	/**
	 * Set the initial values of the game, specially libgdx methods
	 */
	public void create() {
		Texture.setEnforcePotImages(false);
		Gdx.input.setInputProcessor(new MyInputProcessor());
		SB = new SpriteBatch();
		b2dr = new Box2DDebugRenderer();
		this.world = new World(new Vector2(0,0),true);

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		UIManager.getInstance().setSpriteBatch(SB);
		errorFont = new BitmapFont(false);
		errorFont.setColor(Color.RED);
		errorFont.setScale(1.5f,1.5f);

	}
	
	/**
	 * Drops the current WorldManager and resets the UIManager, after that it instances the back end again and generates
	 * the front end from that, setting the observers before
	 */
	public static void newGame(){

		WorldManager.disposeWM();
		UIManager.getInstance().reset();
		Factory.getInstance().setObservers(new BaseObserver(), new UnitObserver(), new PlayerObserver(), new TowerObserver(), new ProjectileObserver());
		UIManager.getInstance().initializeDraws();
		WorldManager.getInstance().getElements().add(WorldManager.getInstance().getPlayer().getBase().getElement());
		WorldManager.getInstance().getElements().add(WorldManager.getInstance().getPlayerAI().getBase().getElement());
		FlyingUnit.setAIAvailable(false);
		FlyingUnit.setPlayerAvailable(false);
		AntiaircraftUnit.setAIAvailable(false);
		AntiaircraftUnit.setPlayerAvailable(false);
		Factory.getInstance().reAssignObservers();
		AI.reset();
		Upgrades.reset();
		gameState = GameState.GAME;
		onGame = true;
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
	
	/**
	 * This method is executed like a loop, calling methods from WorldManager to manage the logic of
	 * the game and methods of UIManager to manage the interface
	 */
	@Override
	public void render() {		

		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if(gameState == GameState.GAME){

			WorldManager.getInstance().updateUnitsQueue();

			WorldManager.getInstance().notifyObservers();

			WorldManager.getInstance().updateUnitsSpeed();

			WorldManager.getInstance().updateElements();

			WorldManager.getInstance().checkCollisions();

			WorldManager.getInstance().updateAttackObjectives();

			WorldManager.getInstance().killUnits();

			AI.getInstance().desitionMaker();

			oneSecondLoop();

			SB.begin();

			UIManager.getInstance().drawBases();
			UIManager.getInstance().drawTextures();
			UIManager.getInstance().drawButtons();
			UIManager.getInstance().drawObjects();
			UIManager.getInstance().drawProjectiles();

			SB.end();

		}else{
			SB.begin();
			if(!firstTimeMenu){
				System.out.println("On Game");
				UIManager.getInstance().drawBases();
				UIManager.getInstance().drawTextures();
				UIManager.getInstance().drawButtons();
				UIManager.getInstance().drawObjects();
				UIManager.getInstance().drawProjectiles();
			}
			SB.draw(Textures.SEMI_TRANSPARENT, 0f, 0f, Game.WIDTH * Game.SCALE, 
					Game.HEIGHT * Game.SCALE, 0, 0, Textures.SEMI_TRANSPARENT.getWidth(), Textures.SEMI_TRANSPARENT.getHeight(), false, false);
			if(!menuDisplayed)
				UIManager.getInstance().drawMenu();
			UIManager.getInstance().drawButtons();
			
			if(UIManager.getInstance().getFileError() && errorDisplayTime>0){
				UIManager.getInstance().DrawFileError(errorFont);
				errorDisplayTime--;
			}
			else if(UIManager.getInstance().getFileError()){
				UIManager.getInstance().setFileError(false);
				errorDisplayTime = ERROR_DISPLAY_TIME;
			}
			
			SB.end();
		}
		b2dr.render(world, cam.combined);
	}
	/**
	 * Save the instances of the backend to a serialized file, and the static values to a csv file
	 */
	public static void saveGame(){

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
			levels = AntiaircraftUnit.getResearch();
			data.add(levels);
			levels = FlyingUnit.getResearch();
			data.add(levels);
			writer.writeAll(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Drop the fronend elements, get the instances of the backend from a serialized file, and the static values from a csv file
	 */

	public static void loadGame(){
		try {
			Object obj;
			FileInputStream fileO = new FileInputStream("WM.ser");
			ObjectInputStream objO = new ObjectInputStream(fileO);
			obj = objO.readObject();
			WorldManager.setInstance((WorldManager)obj);
			objO.close();
			fileO.close();

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
			row = csvReader.readNext();
			AntiaircraftUnit.setResearch(row);
			row = csvReader.readNext();
			FlyingUnit.setResearch(row);
			csvReader.close();
			onGame = true;
			Game.gameState = GameState.GAME;
			
		} catch (FileNotFoundException e) {
			UIManager.getInstance().setFileError(true);
		} catch (IOException e) {
			UIManager.getInstance().setFileError(true);
		} 
		catch (ClassNotFoundException e){
			UIManager.getInstance().setFileError(true);
		}
		
		UIManager.getInstance().reset();
		UIManager.getInstance().initializeDraws();
		Factory.getInstance().setObservers(new BaseObserver(), new UnitObserver(),
				new PlayerObserver(), new TowerObserver(), new ProjectileObserver());
		Factory.getInstance().reAssignObservers();
		AI.reset();
		Upgrades.reset();
		
	}
	
	/**
	 * This method measures 1 second, and add gold to the players every second
	*/
	private void oneSecondLoop(){
		if(secondCount--<=0){
			secondCount = (int) (1/Gdx.graphics.getDeltaTime());
			WorldManager.getInstance().getPlayer().addGold(10);
			WorldManager.getInstance().getPlayerAI().addGold(15);
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

	public static void setOnGame(boolean value){
		onGame = value;
	}

	public static boolean isOnGame(){
		return onGame;
	}

	public static void setFirstTimeMenu(boolean b) {
		firstTimeMenu = b;
	}

	public static boolean isFisrtTimeMenu() {
		return firstTimeMenu;
	}
}

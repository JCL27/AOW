package ar.edu.itba.game.frontend.userinterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.Projectile;
import ar.edu.itba.game.backend.logic.Side;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.Unit;
import ar.edu.itba.game.backend.units.UnitType;
import ar.edu.itba.game.backend.units.UnitsLevels;
import ar.edu.itba.game.frontend.buttons.Button;
import ar.edu.itba.game.frontend.buttons.ContinueButton;
import ar.edu.itba.game.frontend.buttons.ExitButton;
import ar.edu.itba.game.frontend.buttons.LoadButton;
import ar.edu.itba.game.frontend.buttons.NewGameButton;
import ar.edu.itba.game.frontend.buttons.SaveButton;
import ar.edu.itba.game.frontend.drawableobjects.BaseDrawableObject;
import ar.edu.itba.game.frontend.drawableobjects.DrawableObject;
import ar.edu.itba.game.frontend.drawableobjects.Queue;
import ar.edu.itba.game.frontend.drawableobjects.UnitDraw;
import ar.edu.itba.game.frontend.draws.BasicTowerDraw;
import ar.edu.itba.game.frontend.draws.Drawable;
import ar.edu.itba.game.frontend.draws.GrassDraw;
import ar.edu.itba.game.frontend.draws.GroundDraw;
import ar.edu.itba.game.frontend.draws.Textures;
import ar.edu.itba.game.frontend.draws.youLostDraw;
import ar.edu.itba.game.frontend.draws.youWonDraw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 * 
 * This Class manages all the things related to draw elements into the screen
 *
 */
public class UIManager {

	private static UIManager instance = null;

	private final int BUTTON_HEIGHT = 170;
	private final int BUTTON_INITIAL_X = 200;
	private final int BUTTON_SEPARATION = 150;
	private SpriteBatch SB;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private HashMap<Unit, UnitDraw> unitsDraws = new HashMap<Unit, UnitDraw>();
	private HashMap<Projectile, Drawable> projectilesDraws = new HashMap<Projectile, Drawable>();
	private ArrayList<DrawableObject> DOs = new ArrayList<DrawableObject>();
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private Stack<GameUIState> State = new Stack<GameUIState>();

	private Queue playerQueue = new Queue(Side.LEFT);
	private Queue AIQueue = new Queue(Side.RIGHT);
	private BaseDrawableObject playerBase;
	private BaseDrawableObject AIBase;
	private BasicTowerDraw playerTower;

	private BasicTowerDraw AITower;
	private Side looser;


	/**
	 * Labels
	 */
	private Label goldLabel;
	private Label xpLabel;
	private Label rangedUnitLabel;
	private Label meleeUnitLabel;
	private Label antiaircraftUnitLabel;
	private Label flyingUnitLabel;

	/**
	 * Labels Styles
	 */
	private LabelStyle goldLabelStyle;
	private LabelStyle xpLabelStyle;
	private LabelStyle rangedLabelStyle;
	private LabelStyle meleeLabelStyle;
	private LabelStyle antiaircraftLabelStyle;
	private LabelStyle flyingLabelStyle;

	/**
	 * Text font and Background for the Labels
	 */
	private NinePatch labelBackground;
	private NinePatch hiddenLabelBackground;
	private BitmapFont font;
	private Skin labelSkin;
	private Skin hiddenLabelSkin;

	/**
	 * States whether the Units Stats Labels are visible or not
	 */
	private boolean rangedLabelvisible = false;
	private boolean flyingLabelvisible = false;
	private boolean antiaircraftLabelvisible = false;
	private boolean meleeLabelvisible = false;
	
	private boolean fileError = false;
	
	public static UIManager getInstance() {
		if(instance == null) {
			instance = new UIManager();
		}
		return instance;
	}

	public ArrayList<DrawableObject> getDOs() {
		return DOs;
	}

	private UIManager(){
		this.looser = null;
	}

	public void reset(){
		this.DOs.clear();
		this.drawables.clear();
		this.projectilesDraws.clear();
		this.unitsDraws.clear();
	}

	public void setLooser(Side side){
		this.looser = side;
	}

	/**
	 * Set the initial values for the draws that have to be drawn at the start of the game
	 * and set the values of the labels that are going to be used
	 */
	public void initializeDraws(){
		Game.setFirstTimeMenu(false);

		this.drawables.add(new GroundDraw(0,0));
		this.drawables.add(new GrassDraw(270f , Game.GROUND_HEIGHT - 10, 50, 50));

		DOs.add(this.playerQueue);
		DOs.add(this.AIQueue);

		State.add(State.push(GameUIState.DEFAULT));
		this.updateButtons();

		//Text font used for labels
		this.font = new BitmapFont(false);
		font.setColor(Color.GREEN);

		/**
		 * NinePatch: Sets rectangle with the size of the given sides.
		 */
		this.labelBackground = new NinePatch(Textures.GROUND, GameStats.BACKGROUND_LEFT_SIZE, GameStats.BACKGROUND_RIGHT_SIZE, 
				GameStats.BACKGROUND_BOTTOM_SIZE, GameStats.BACKGROUND_TOP_SIZE);
		this.hiddenLabelBackground = new NinePatch(Textures.GROUND, GameStats.BACKGROUND_LEFT_SIZE, GameStats.BACKGROUND_RIGHT_SIZE, 
				GameStats.HIDDEN_BACKGROUND_BOTTOM_SIZE, GameStats.BACKGROUND_TOP_SIZE);
		this.labelSkin = new Skin();
		this.hiddenLabelSkin = new Skin();
		this.labelSkin.add("background", labelBackground);
		this.hiddenLabelSkin.add("background", hiddenLabelBackground);

		/**
		 * Set Styles for labels
		 */
		this.goldLabelStyle = new LabelStyle(font, Color.YELLOW);
		this.xpLabelStyle = new LabelStyle(font, Color.GREEN);
		this.rangedLabelStyle = new LabelStyle(font, Color.GREEN);
		this.meleeLabelStyle = new LabelStyle(font, Color.GREEN);
		this.antiaircraftLabelStyle = new LabelStyle(font, Color.GREEN);
		this.flyingLabelStyle = new LabelStyle(font, Color.GREEN);

		/**
		 * Add the background to the labelStyles
		 */
		this.goldLabelStyle.background = labelSkin.getDrawable("background");
		this.xpLabelStyle.background = labelSkin.getDrawable("background");
		setDefaultLabelsText();

		this.goldLabel = new Label("" , this.goldLabelStyle);
		this.xpLabel = new Label("", this.xpLabelStyle);
		this.rangedUnitLabel = new Label("", this.rangedLabelStyle);
		this.meleeUnitLabel = new Label("", this.meleeLabelStyle);
		this.antiaircraftUnitLabel = new Label("", this.antiaircraftLabelStyle);
		this.flyingUnitLabel = new Label("", this.flyingLabelStyle);

		this.goldLabel.setPosition(GameStats.GOLD_LABEL_X, GameStats.GOLD_LABEL_Y);
		this.xpLabel.setPosition(GameStats.EXP_LABEL_X, GameStats.EXP_LABEL_Y);
		this.rangedUnitLabel.setPosition(GameStats.LABEL_RANGED_X, GameStats.LABEL_UNITS_Y);
		this.meleeUnitLabel.setPosition(GameStats.LABEL_MELEE_X, GameStats.LABEL_UNITS_Y);
		this.antiaircraftUnitLabel.setPosition(GameStats.LABEL_ANTIAIRCRAFT_X, GameStats.LABEL_UNITS_Y);
		this.flyingUnitLabel.setPosition(GameStats.LABEL_FLYING_X, GameStats.LABEL_UNITS_Y);

	}
	/**
	 * Reset the labels backgrounds and refreshes the current gold, exp and units level
	 */

	private void setDefaultLabelsText(){
		this.rangedLabelStyle.background = labelSkin.getDrawable("background");
		this.meleeLabelStyle.background = labelSkin.getDrawable("background");
		this.antiaircraftLabelStyle.background = labelSkin.getDrawable("background");
		this.flyingLabelStyle.background = labelSkin.getDrawable("background");
	}
	
	private void setDefaultLabelsBackground(){
		Player player = WorldManager.getInstance().getPlayer();
		
		this.goldLabel.setText("Gold: " + player.getGold().toString());
		this.xpLabel.setText("XP: " + player.getExp().toString());
		this.rangedUnitLabel.setText("Ranged Level: " + UnitsLevels.getInstance().getLevel(player, UnitType.RANGED_UNIT));
		this.meleeUnitLabel.setText("Melee Level: " + UnitsLevels.getInstance().getLevel(player, UnitType.MELEE_UNIT));
		this.antiaircraftUnitLabel.setText("AntiAircraft Level: " + UnitsLevels.getInstance().getLevel(player, UnitType.ANTIAIRCRAFT_UNIT));
		this.flyingUnitLabel.setText("Flying Level: " + UnitsLevels.getInstance().getLevel(player, UnitType.FLYING_UNIT));   
	}

	public void setPlayerBase(BaseDrawableObject playerBase) {
		this.playerBase = playerBase;
	}

	public void setAIBase(BaseDrawableObject aIBase) {
		this.AIBase = aIBase;
	}

	public void setSpriteBatch(SpriteBatch SB){
		this.SB = SB;
	}

	public void pushState(GameUIState state){
		this.State.push(state);
	}

	public void popState(){
		this.State.pop();
	}

	public void updateButtonsState(){
		for(Button button:this.buttons){
			button.checkAvailability();
		}
	}
	
	/**
	 * Add to the buttons array the buttons that should be displayed at the moment depending on
	 * the current UIGameState
	 */
	public void updateButtons(){
		int count = 0;
		this.buttons.clear();
		switch(State.peek()){
		case DEFAULT:
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.CreateUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.TowerButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.UpgradeUnitButton(this.BUTTON_INITIAL_X + count++ *this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.UpgradeTowerButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.MenuButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case CREATE_UNIT:
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.CreateRangedUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.CreateFlyingUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.CreateAntiaircraftUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.CreateMeleeUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case TOWER:
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.CreateBasicTowerButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.DisposeTowerButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case UPGRADE_UNIT:
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.UpgradeMeleeUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.UpgradeRangedUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.UpgradeFlyingUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.UpgradeAntiaircraftUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.ResearchAntiaircraftUnitsButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.ResearchFlyingUnitsButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case UPGRADE_TOWER:
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.TowerDamageUpgradeButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.TowerAttackSpeedUpgradeButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.TowerAttackRangeUpgradeButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		}

		if(!State.peek().equals(GameUIState.DEFAULT)){
			this.buttons.add(new ar.edu.itba.game.frontend.buttons.ReturnButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
		}
	}

	public ArrayList<Drawable> getDraws(){
		return this.drawables;
	}

	public ArrayList<Button> getButtons() {
		return this.buttons;
	}

	public void drawBases(){
		this.drawObject(this.playerBase);
		this.drawObject(this.AIBase);
	}

	public void drawProjectiles(){
		for(Drawable draw:this.projectilesDraws.values()){
			this.drawTexture(draw);
		}
	}
	
	public void drawTextures(){
		Player player = WorldManager.getInstance().getPlayer();

		for(Drawable draw:this.drawables){
			this.drawTexture(draw);
		}
		this.drawButtonsMessage();
		this.setDefaultLabelsText();
		this.setDefaultLabelsBackground();
		this.goldLabel.draw(SB, 1);
		this.xpLabel.draw(SB, 1);

		if(this.flyingLabelvisible){
			int level = UnitsLevels.getInstance().getLevel(player, UnitType.FLYING_UNIT);
			this.flyingUnitLabel.getStyle().background = hiddenLabelSkin.getDrawable("background");
			this.flyingUnitLabel.draw(SB, 1);
			font.draw(SB, "HP: " + (int)(GameStats.FLYING_UNIT_MAX_HP + Math.sqrt(level * GameStats.FLYING_UNIT_MAX_HP_UPGRADE_RATE)), GameStats.LABEL_FLYING_X + 10, GameStats.LABEL_UNITS_Y -5);
			font.draw(SB, "Attack Speed: " + (int)(GameStats.FLYING_UNIT_ATTACK_SPEED + Math.sqrt(level * GameStats.FLYING_UNIT_ATTACK_SPEED_UPGRADE_RATE)), GameStats.LABEL_FLYING_X + 10, GameStats.LABEL_UNITS_Y -20);
			font.draw(SB, "Attack Range: " + (int)(GameStats.FLYING_UNIT_ATTACK_RANGE + Math.sqrt(level * GameStats.FLYING_UNIT_ATTACK_RANGE_UPGRADE_RATE)), GameStats.LABEL_FLYING_X + 10, GameStats.LABEL_UNITS_Y - 35);
			font.draw(SB, "Attack Damage: " + (int)(GameStats.FLYING_UNIT_DAMAGE + Math.sqrt(level * GameStats.FLYING_UNIT_DAMAGE_UPGRADE_RATE)), GameStats.LABEL_FLYING_X + 10, GameStats.LABEL_UNITS_Y - 50);
			font.draw(SB, "Movement Speed: " + (int)(GameStats.FLYING_UNIT_MOVEMENT_SPEED + Math.sqrt(level * GameStats.FLYING_UNIT_MOVEMENT_SPEED_UPGRADE_RATE)), GameStats.LABEL_FLYING_X + 10, GameStats.LABEL_UNITS_Y - 65);
			font.draw(SB, "Gold Cost: " + (int) (GameStats.FLYING_UNIT_COST + Math.sqrt(level * GameStats.FLYING_UNIT_COST_UPGRADE_RATE)), GameStats.LABEL_FLYING_X + 10, GameStats.LABEL_UNITS_Y - 80);

		}
		else
			this.flyingUnitLabel.draw(SB, 1);

		if(this.meleeLabelvisible){
			int level = UnitsLevels.getInstance().getLevel(player, UnitType.MELEE_UNIT);
			this.meleeUnitLabel.getStyle().background = hiddenLabelSkin.getDrawable("background");
			this.meleeUnitLabel.draw(SB, 1);
			font.draw(SB, "HP: " + (int)(GameStats.MELEE_UNIT_MAX_HP + Math.sqrt(level * GameStats.MELEE_UNIT_MAX_HP_UPGRADE_RATE)), GameStats.LABEL_MELEE_X + 10, GameStats.LABEL_UNITS_Y -5);
			font.draw(SB, "Attack Speed: " + (int)(GameStats.MELEE_UNIT_ATTACK_SPEED + Math.sqrt(level * GameStats.MELEE_UNIT_ATTACK_SPEED_UPGRADE_RATE)), GameStats.LABEL_MELEE_X + 10, GameStats.LABEL_UNITS_Y -20);
			font.draw(SB, "Attack Range: " + (int)(GameStats.MELEE_UNIT_ATTACK_RANGE + Math.sqrt(level * GameStats.MELEE_UNIT_ATTACK_RANGE_UPGRADE_RATE)), GameStats.LABEL_MELEE_X + 10, GameStats.LABEL_UNITS_Y -35);
			font.draw(SB, "Attack Damage: " + (int)(GameStats.MELEE_UNIT_DAMAGE + Math.sqrt(level * GameStats.MELEE_UNIT_DAMAGE_UPGRADE_RATE)), GameStats.LABEL_MELEE_X + 10, GameStats.LABEL_UNITS_Y -50);
			font.draw(SB, "Movement Speed: " + (int)(GameStats.MELEE_UNIT_MOVEMENT_SPEED + Math.sqrt(level * GameStats.MELEE_UNIT_MOVEMENT_SPEED_UPGRADE_RATE)), GameStats.LABEL_MELEE_X + 10, GameStats.LABEL_UNITS_Y -65);
			font.draw(SB, "Gold Cost: " + (int) (GameStats.MELEE_UNIT_COST + Math.sqrt(level * GameStats.MELEE_UNIT_COST_UPGRADE_RATE)), GameStats.LABEL_MELEE_X + 10, GameStats.LABEL_UNITS_Y -80);
		}
		else
			this.meleeUnitLabel.draw(SB, 1);

		if(this.rangedLabelvisible){
			int level = UnitsLevels.getInstance().getLevel(player, UnitType.RANGED_UNIT);
			this.rangedUnitLabel.getStyle().background = hiddenLabelSkin.getDrawable("background");
			this.rangedUnitLabel.draw(SB, 1);
			font.draw(SB, "HP: " + (int)(GameStats.RANGED_UNIT_MAX_HP + Math.sqrt(level * GameStats.RANGED_UNIT_MAX_HP_UPGRADE_RATE)),GameStats.LABEL_RANGED_X + 10, GameStats.LABEL_UNITS_Y -5);
			font.draw(SB, "Attack Speed: " + (int)(GameStats.RANGED_UNIT_ATTACK_SPEED + Math.sqrt(level * GameStats.RANGED_UNIT_ATTACK_SPEED_UPGRADE_RATE)), GameStats.LABEL_RANGED_X + 10, GameStats.LABEL_UNITS_Y -20);
			font.draw(SB, "Attack Range: " + (int)(GameStats.RANGED_UNIT_ATTACK_RANGE + Math.sqrt(level * GameStats.RANGED_UNIT_ATTACK_RANGE_UPGRADE_RATE)), GameStats.LABEL_RANGED_X + 10, GameStats.LABEL_UNITS_Y -35);
			font.draw(SB, "Attack Damage: " + (int)(GameStats.RANGED_UNIT_DAMAGE + Math.sqrt(level * GameStats.RANGED_UNIT_DAMAGE_UPGRADE_RATE)), GameStats.LABEL_RANGED_X + 10, GameStats.LABEL_UNITS_Y -50);
			font.draw(SB, "Movement Speed: " + (int)(GameStats.RANGED_UNIT_MOVEMENT_SPEED + Math.sqrt(level * GameStats.RANGED_UNIT_MOVEMENT_SPEED_UPGRADE_RATE)), GameStats.LABEL_RANGED_X + 10, GameStats.LABEL_UNITS_Y -65);
			font.draw(SB, "Gold Cost: " + (int) (GameStats.RANGED_UNIT_COST + Math.sqrt(level * GameStats.RANGED_UNIT_COST_UPGRADE_RATE)), GameStats.LABEL_RANGED_X + 10, GameStats.LABEL_UNITS_Y -80);
		}
		else
			this.rangedUnitLabel.draw(SB, 1);


		if(this.antiaircraftLabelvisible){
			int level = UnitsLevels.getInstance().getLevel(player, UnitType.ANTIAIRCRAFT_UNIT);
			this.antiaircraftUnitLabel.getStyle().background = hiddenLabelSkin.getDrawable("background");
			this.antiaircraftUnitLabel.draw(SB, 1);
			font.draw(SB, "HP: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_MAX_HP + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_MAX_HP_UPGRADE_RATE)), GameStats.LABEL_ANTIAIRCRAFT_X + 10, GameStats.LABEL_UNITS_Y -5);
			font.draw(SB, "Attack Speed: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_ATTACK_SPEED + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_ATTACK_SPEED_UPGRADE_RATE)), GameStats.LABEL_ANTIAIRCRAFT_X + 10, GameStats.LABEL_UNITS_Y -20);
			font.draw(SB, "Attack Range: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_ATTACK_RANGE + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_ATTACK_RANGE_UPGRADE_RATE)), GameStats.LABEL_ANTIAIRCRAFT_X + 10, GameStats.LABEL_UNITS_Y -35);
			font.draw(SB, "Attack Damage: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_DAMAGE + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_DAMAGE_UPGRADE_RATE)), GameStats.LABEL_ANTIAIRCRAFT_X + 10, GameStats.LABEL_UNITS_Y -50);
			font.draw(SB, "Movement Speed: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED_UPGRADE_RATE)), GameStats.LABEL_ANTIAIRCRAFT_X + 10, GameStats.LABEL_UNITS_Y -65);
			font.draw(SB, "Gold Cost: " + (int) (GameStats.ANTIAIRCRAFT_UNIT_COST + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_COST_UPGRADE_RATE)), GameStats.LABEL_ANTIAIRCRAFT_X + 10, GameStats.LABEL_UNITS_Y -80);
		}
		else
			this.antiaircraftUnitLabel.draw(this.SB, 1);
	}

	private void drawTexture(Drawable draw){
		this.SB.draw(draw.getTexture(), (float)draw.getxPos(), (float) draw.getyPos(), draw.getScreenWidth(), 
				draw.getScreenHeight(), 0, 0, draw.getSpriteWidth(), draw.getSpriteHeight(), false, false);
	}

	public void drawButtons(){
		this.updateButtonsState();
		for(Button button: this.buttons){
			this.drawButton(button);
		}
	}
	
	public void drawButtonsMessage(){
		for(Button button : this.buttons){
			if(button.getMessageVisibility())
				button.showMessage(this.SB);
		}
	}

	private void drawButton(Button button){
		this.SB.draw(button.getDraw().getTexture(), button.getDraw().getxPos(), button.getDraw().getyPos(), 
				button.getDraw().getScreenWidth() ,button.getDraw().getScreenHeight());
	}

	public void drawObjects(){
		for(DrawableObject DO: this.DOs){
			this.drawObject(DO);
		}

		for(DrawableObject DO: this.unitsDraws.values()){
			this.drawObject(DO);
		}
	}

	public BasicTowerDraw getPlayerTower() {
		return playerTower;
	}

	public BasicTowerDraw getAITower() {
		return AITower;
	}

	private void drawObject(DrawableObject DO){
		for(Drawable draw:DO.getDraws()){
			this.drawTexture(draw);
		}
	}
	
	public Queue getPlayerQueue() {
		return playerQueue;
	}

	public Queue getAIQueue() {
		return AIQueue;
	}

	public BaseDrawableObject getPlayerBase() {
		return playerBase;
	}

	public BaseDrawableObject getAIBase() {
		return AIBase;
	}

	public void setPlayerTower(BasicTowerDraw playerTower) {
		this.playerTower = playerTower;
	}

	public void setAITower(BasicTowerDraw aITower) {
		AITower = aITower;
	}

	public HashMap<Projectile, Drawable> getProjectilesDraws() {
		return projectilesDraws;
	}

	public void setRangedLabelvisible(boolean rangedLabelvisible) {
		this.rangedLabelvisible = rangedLabelvisible;
	}

	public void setFlyingLabelvisible(boolean flyingLabelvisible) {
		this.flyingLabelvisible = flyingLabelvisible;
	}

	public void setAntiaircraftLabelvisible(boolean antiAircraftLabelvisible) {
		this.antiaircraftLabelvisible = antiAircraftLabelvisible;
	}

	public void setMeleeLabelvisible(boolean meleeLabelvisible) {
		this.meleeLabelvisible = meleeLabelvisible;
	}

	
	
	public void setFileError(boolean error){
		this.fileError = error;
	}
	
	public boolean getFileError(){
		return this.fileError;
	}
	
	public void DrawFileError(BitmapFont errorFont){
		errorFont.draw(SB, "Error: File Not Found", 750, 300);
		
	}

	
	/**
	 * Draw the main menu and the loose/win draw if the game has ended
	 */

	public void drawMenu() {

		buttons.clear();
		if(Game.isOnGame()){
			buttons.add(new ContinueButton(430, 625));
			buttons.add(new NewGameButton(430, 500));
			buttons.add(new SaveButton(430, 375));
			buttons.add(new LoadButton(430, 250));
			buttons.add(new ExitButton(480, 125));
		}else{
			
			if(!Game.isFisrtTimeMenu()){
				if(this.looser==Side.LEFT){
					this.drawTexture(new youLostDraw(380, 550, 150, 400));
				}else{
					this.drawTexture(new youWonDraw(380, 550, 150, 400));
				}
			}
			buttons.add(new NewGameButton(430, 425));
			buttons.add(new LoadButton(430, 250));
			buttons.add(new ExitButton(480, 75));
		}
	}

	public HashMap<Unit, UnitDraw> getUnitsDraws() {
		return this.unitsDraws;
	}


}

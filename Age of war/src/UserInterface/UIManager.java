package UserInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import Buttons.Button;
import DrawableObjects.BaseDrawableObject;
import DrawableObjects.DrawableObject;
import DrawableObjects.Queue;
import DrawableObjects.UnitDraw;
import Draws.BasicTowerDraw;
import java.util.Set;
import Buttons.CreateRangedUnit;
import Draws.Drawable;
import Draws.GrassDraw;
import Draws.GroundDraw;
import Draws.Textures;
import Units.AntiaircraftUnit;
import Units.FlyingUnit;
import Units.MeleeUnit;
import Units.RangedUnit;

import Units.Unit;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Projectile;
import ar.edu.itba.game.Side;

import ar.edu.itba.game.Element;
import ar.edu.itba.game.Type;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;



public class UIManager {
	
	private static UIManager instance = null;
	
	private final int BUTTON_HEIGHT = 150;
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

	public HashMap<Unit, UnitDraw> getUnitsDraws() {
		return unitsDraws;
	}

	//Labels
	private Label goldLabel;
	private Label xpLabel;
	private Label rangedUnitLabel;
	private Label meleeUnitLabel;
	private Label antiaircraftUnitLabel;
	private Label flyingUnitLabel;
	
	//Labels Styles
	private LabelStyle goldLabelStyle;
	private LabelStyle xpLabelStyle;
	private LabelStyle rangedLabelStyle;
	private LabelStyle meleeLabelStyle;
	private LabelStyle antiaircraftLabelStyle;
	private LabelStyle flyingLabelStyle;
	
	//Text font and Background for the Labels
	private NinePatch labelBackground;
	private NinePatch hiddenLabelBackground;
	private BitmapFont font;
	private Skin labelSkin;
	private Skin hiddenLabelSkin;
	
	//States whether the Units Stats Labels are visible or not
	private boolean rangedLabelvisible = false;
	private boolean flyingLabelvisible = false;
	private boolean antiaircraftLabelvisible = false;
	private boolean meleeLabelvisible = false;
	
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
		
	}
	
	public void reset(){
		this.DOs.clear();
		this.drawables.clear();
		this.projectilesDraws.clear();
		this.unitsDraws.clear();
	}
	
	public void initializeDraws(){
		this.drawables.add(new GroundDraw(0,0));
		this.drawables.add(new GrassDraw(270f , Game.GROUND_HEIGHT - 10, 50, 50));
		
		DOs.add(this.playerQueue);
		DOs.add(this.AIQueue);
		
		State.add(State.push(GameUIState.DEFAULT));
		this.updateButtons();
		
		//Text font used for labels
		this.font = new BitmapFont(false);
		font.setColor(Color.GREEN);
		
		//NinePatch: Sets rectangle of given sides.
		this.labelBackground = new NinePatch(Textures.GROUND, 10, 145, 10, 10);
		this.hiddenLabelBackground = new NinePatch(Textures.GROUND, 10, 145, 120, 10);
		this.labelSkin = new Skin();
		this.hiddenLabelSkin = new Skin();
		this.labelSkin.add("background", labelBackground);
		this.hiddenLabelSkin.add("background", hiddenLabelBackground);
		
		//Set Styles for labels
		this.goldLabelStyle = new LabelStyle(font, Color.YELLOW);
		this.xpLabelStyle = new LabelStyle(font, Color.GREEN);
		this.rangedLabelStyle = new LabelStyle(font, Color.GREEN);
		this.meleeLabelStyle = new LabelStyle(font, Color.GREEN);
		this.antiaircraftLabelStyle = new LabelStyle(font, Color.GREEN);
		this.flyingLabelStyle = new LabelStyle(font, Color.GREEN);
		
		//Add the background to the labelStyles
		//ACOMODAR!!!
		this.goldLabelStyle.background = labelSkin.getDrawable("background");
		this.xpLabelStyle.background = labelSkin.getDrawable("background");
		this.rangedLabelStyle.background = labelSkin.getDrawable("background");
		this.meleeLabelStyle.background = labelSkin.getDrawable("background");
		this.antiaircraftLabelStyle.background = labelSkin.getDrawable("background");
		this.flyingLabelStyle.background = labelSkin.getDrawable("background");
		
		this.goldLabel = new Label("" , this.goldLabelStyle);
		this.xpLabel = new Label("", this.xpLabelStyle);
		this.rangedUnitLabel = new Label("", this.rangedLabelStyle);
		this.meleeUnitLabel = new Label("", this.meleeLabelStyle);
		this.antiaircraftUnitLabel = new Label("", this.antiaircraftLabelStyle);
		this.flyingUnitLabel = new Label("", this.flyingLabelStyle);
		
		//The background is drawn with the origin set in the give position.
        this.goldLabel.setPosition(15, 750);
        this.xpLabel.setPosition(15, 690);
        this.rangedUnitLabel.setPosition(200, 750);
        this.meleeUnitLabel.setPosition(400, 750);
        this.antiaircraftUnitLabel.setPosition(600, 750);
        this.flyingUnitLabel.setPosition(800, 750);
		
	}
	
	private void setDefaultLabels(){
		this.rangedLabelStyle.background = labelSkin.getDrawable("background");
		this.meleeLabelStyle.background = labelSkin.getDrawable("background");
		this.antiaircraftLabelStyle.background = labelSkin.getDrawable("background");
		this.flyingLabelStyle.background = labelSkin.getDrawable("background");
		
		this.goldLabel.setText("Gold: " + WorldManager.getInstance().getPlayer().getGold().toString());
        this.xpLabel.setText("XP: " + WorldManager.getInstance().getPlayer().getExp().toString());
        this.rangedUnitLabel.setText("Ranged Level: " + RangedUnit.getPlayerUnitLevel());
        this.meleeUnitLabel.setText("Melee Level: " + MeleeUnit.getPlayerUnitLevel());
        this.antiaircraftUnitLabel.setText("AntiAircraft Level: " + AntiaircraftUnit.getPlayerUnitLevel());
        this.flyingUnitLabel.setText("Flying Level: " + FlyingUnit.getPlayerUnitLevel());   

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
		Player player = WorldManager.getInstance().getPlayer();
		for(Button button:this.buttons){
			switch(button.getClass().getSimpleName()){
			case("CreateAntiaircraftUnit"):
				if(player.getGold()<AntiaircraftUnit.getCost(player) || !AntiaircraftUnit.isPlayerAvailable())
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("CreateBasicTower"):
				if((player.getGold()<GameStats.TOWER_COST) || (player.getTower()!=null))
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("CreateFlyingUnit"):
				if(player.getGold()<FlyingUnit.getCost(player) || !FlyingUnit.isPlayerAvailable())
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("CreateMeleeUnit"):
				
				if(player.getGold()<MeleeUnit.getCost(player)){
					button.getDraw().setDisabled();
				}else{
					button.getDraw().setEnabled();
				}break;
			case("CreateRangedUnit"):
				if(player.getGold()<RangedUnit.getCost(player))
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("TowerAttackRangeUpgradeButton"):
				if(player.getExp()<GameStats.TOWER_ATTACK_RANGE_UPGRADE_COST || (player.getTower()==null) || player.getTower().isUpgradedAttackRange())
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("TowerAttackSpeedUpgradeButton"):
				if(player.getExp()<GameStats.TOWER_ATTACK_SPEED_UPGRADE_COST || (player.getTower()==null) || player.getTower().isUpgradedAttackSpeed())
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("TowerDamageUpgradeButton"):
				if(player.getExp()<GameStats.TOWER_DAMAGE_UPGRADE_COST || (player.getTower()==null) || player.getTower().isUpgradedDamage())
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("DisposeTower"):
				if(player.getTower()==null)
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("ResearchAntiaircraftUnitsButton"):
				if(player.getExp()<GameStats.ANTIAIRCRAFT_UNIT_RESEARCH_COST || AntiaircraftUnit.isPlayerAvailable())
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("ResearchFlyingUnitsButton"):
				if(player.getExp()<GameStats.FLYING_UNIT_RESEARCH_COST || FlyingUnit.isPlayerAvailable())
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("UpgradeAntiaircraftUnitButton"):
				if(player.getExp()<GameStats.UNIT_UPGRADE_COST)
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("UpgradeFlyingUnitButton"):
				if(player.getExp()<GameStats.UNIT_UPGRADE_COST)
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("UpgradeMeleeUnitButton"):
				if(player.getExp()<GameStats.UNIT_UPGRADE_COST)
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			case("UpgradeRangedUnitButton"):
				if(player.getExp()<GameStats.UNIT_UPGRADE_COST)
					button.getDraw().setDisabled();
				else
					button.getDraw().setEnabled();
				break;
			}
		}
	}
	
	public void updateButtons(){
		int count = 0;
		this.buttons.clear();
		switch(State.peek()){
		case DEFAULT:
			this.buttons.add(new Buttons.CreateUnit(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.Tower(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.UpgradeUnitButton(this.BUTTON_INITIAL_X + count++ *this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.UpgradeTowerButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.MenuButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case CREATE_UNIT:
			this.buttons.add(new Buttons.CreateRangedUnit(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.CreateFlyingUnit(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.CreateAntiaircraftUnit(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.CreateMeleeUnit(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case TOWER:
			this.buttons.add(new Buttons.CreateBasicTower(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.DisposeTower(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case UPGRADE_UNIT:
			this.buttons.add(new Buttons.UpgradeMeleeUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.UpgradeRangedUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.UpgradeFlyingUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.UpgradeAntiaircraftUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.ResearchAntiaircraftUnitsButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.ResearchFlyingUnitsButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));

			break;
		case UPGRADE_TOWER:
			this.buttons.add(new Buttons.TowerDamageUpgradeButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.TowerAttackSpeedUpgradeButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.TowerAttackRangeUpgradeButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		}
		
		if(!State.peek().equals(GameUIState.DEFAULT)){
			this.buttons.add(new Buttons.Back(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
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
		for(Drawable draw:this.drawables){
			this.drawTexture(draw);
		}
		
		this.setDefaultLabels();
		this.goldLabel.draw(SB, 1);
		this.xpLabel.draw(SB, 1);
		
		if(flyingLabelvisible){
			int level = RangedUnit.getPlayerUnitLevel();
        	this.flyingUnitLabel.getStyle().background = hiddenLabelSkin.getDrawable("background");
        	this.flyingUnitLabel.draw(SB, 1);
        	font.draw(SB, "HP: " + (int)(GameStats.FLYING_UNIT_MAX_HP + Math.sqrt(level * GameStats.FLYING_UNIT_MAX_HP_UPGRADE_RATE)), 810, 745);
        	font.draw(SB, "Attack Speed: " + (int)(GameStats.FLYING_UNIT_ATTACK_SPEED + Math.sqrt(level * GameStats.FLYING_UNIT_ATTACK_SPEED_UPGRADE_RATE)), 810, 730);
        	font.draw(SB, "Attack Range: " + (int)(GameStats.FLYING_UNIT_ATTACK_RANGE + Math.sqrt(level * GameStats.FLYING_UNIT_ATTACK_RANGE_UPGRADE_RATE)), 810, 715);
        	font.draw(SB, "Attack Damage: " + (int)(GameStats.FLYING_UNIT_DAMAGE + Math.sqrt(level * GameStats.FLYING_UNIT_DAMAGE_UPGRADE_RATE)), 810, 700);
        	font.draw(SB, "Movement Speed: " + (int)(GameStats.FLYING_UNIT_MOVEMENT_SPEED + Math.sqrt(level * GameStats.FLYING_UNIT_MOVEMENT_SPEED_UPGRADE_RATE)), 810, 685);
        }
		else
			this.flyingUnitLabel.draw(SB, 1);
					
		if(meleeLabelvisible){
			int level = MeleeUnit.getPlayerUnitLevel();
        	this.meleeUnitLabel.getStyle().background = hiddenLabelSkin.getDrawable("background");
        	this.meleeUnitLabel.draw(SB, 1);
        	font.draw(SB, "HP: " + (int)(GameStats.MELEE_UNIT_MAX_HP + Math.sqrt(level * GameStats.MELEE_UNIT_MAX_HP_UPGRADE_RATE)), 410, 745);
        	font.draw(SB, "Attack Speed: " + (int)(GameStats.MELEE_UNIT_ATTACK_SPEED + Math.sqrt(level * GameStats.MELEE_UNIT_ATTACK_SPEED_UPGRADE_RATE)), 410, 730);
        	font.draw(SB, "Attack Range: " + (int)(GameStats.MELEE_UNIT_ATTACK_RANGE + Math.sqrt(level * GameStats.MELEE_UNIT_ATTACK_RANGE_UPGRADE_RATE)), 410, 715);
        	font.draw(SB, "Attack Damage: " + (int)(GameStats.MELEE_UNIT_DAMAGE + Math.sqrt(level * GameStats.MELEE_UNIT_DAMAGE_UPGRADE_RATE)), 410, 700);
        	font.draw(SB, "Movement Speed: " + (int)(GameStats.MELEE_UNIT_MOVEMENT_SPEED + Math.sqrt(level * GameStats.MELEE_UNIT_MOVEMENT_SPEED_UPGRADE_RATE)), 410, 685);
        }
		else
			this.meleeUnitLabel.draw(SB, 1);
        
		if(rangedLabelvisible){
			int level = RangedUnit.getPlayerUnitLevel();
        	this.rangedUnitLabel.getStyle().background = hiddenLabelSkin.getDrawable("background");
        	this.rangedUnitLabel.draw(SB, 1);
        	font.draw(SB, "HP: " + (int)(GameStats.RANGED_UNIT_MAX_HP + Math.sqrt(level * GameStats.RANGED_UNIT_MAX_HP_UPGRADE_RATE)), 210, 745);
        	font.draw(SB, "Attack Speed: " + (int)(GameStats.RANGED_UNIT_ATTACK_SPEED + Math.sqrt(level * GameStats.RANGED_UNIT_ATTACK_SPEED_UPGRADE_RATE)), 210, 730);
        	font.draw(SB, "Attack Range: " + (int)(GameStats.RANGED_UNIT_ATTACK_RANGE + Math.sqrt(level * GameStats.RANGED_UNIT_ATTACK_RANGE_UPGRADE_RATE)), 210, 715);
        	font.draw(SB, "Attack Damage: " + (int)(GameStats.RANGED_UNIT_DAMAGE + Math.sqrt(level * GameStats.RANGED_UNIT_DAMAGE_UPGRADE_RATE)), 210, 700);
        	font.draw(SB, "Movement Speed: " + (int)(GameStats.RANGED_UNIT_MOVEMENT_SPEED + Math.sqrt(level * GameStats.RANGED_UNIT_MOVEMENT_SPEED_UPGRADE_RATE)), 210, 685);
        }
		else
			this.rangedUnitLabel.draw(SB, 1);
		
		
		if(antiaircraftLabelvisible){
			int level = RangedUnit.getPlayerUnitLevel();
        	this.antiaircraftUnitLabel.getStyle().background = hiddenLabelSkin.getDrawable("background");
        	this.antiaircraftUnitLabel.draw(SB, 1);
        	font.draw(SB, "HP: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_MAX_HP + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_MAX_HP_UPGRADE_RATE)), 610, 745);
        	font.draw(SB, "Attack Speed: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_ATTACK_SPEED + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_ATTACK_SPEED_UPGRADE_RATE)), 610, 730);
        	font.draw(SB, "Attack Range: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_ATTACK_RANGE + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_ATTACK_RANGE_UPGRADE_RATE)), 610, 715);
        	font.draw(SB, "Attack Damage: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_DAMAGE + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_DAMAGE_UPGRADE_RATE)), 610, 700);
        	font.draw(SB, "Movement Speed: " + (int)(GameStats.ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED + Math.sqrt(level * GameStats.ANTIAIRCRAFT_UNIT_MOVEMENT_SPEED_UPGRADE_RATE)), 610, 685);
        }
		else
			this.antiaircraftUnitLabel.draw(SB, 1);
		   
	}
	
	private void drawTexture(Drawable draw){
		SB.draw(draw.getTexture(), (float)draw.getxPos(), (float) draw.getyPos(), draw.getScreenWidth(), 
				draw.getScreenHeight(), 0, 0, draw.getSpriteWidth(), draw.getSpriteHeight(), false, false);
	}
	
	public void drawButtons(){
		this.updateButtonsState();
		for(Button button: buttons){
			this.drawButton(button);
		}
	}
	
	private void drawButton(Button button){
		SB.draw(button.getDraw().getTexture(), (float)button.getDraw().getxPos(), (float)button.getDraw().getyPos(), 
				button.getDraw().getScreenHeight(), button.getDraw().getScreenWidth());
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
		

}

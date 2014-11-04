package UserInterface;

import java.util.ArrayList;
import java.util.Stack;

import Buttons.Button;
import Draws.Drawable;
import Draws.GrassDraw;
import Draws.GroundDraw;
import Draws.Textures;
import Units.AntiaircraftUnit;
import Units.FlyingUnit;
import Units.MeleeUnit;
import Units.RangedUnit;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class UIManager {
	
	private static UIManager instance = null;
	
	private final int BUTTON_HEIGHT = 150;
	private final int BUTTON_INITIAL_X = 200;
	private final int BUTTON_SEPARATION = 150;
	private SpriteBatch SB;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<DrawableObject> DOs = new ArrayList<DrawableObject>();
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private Stack<GameUIState> State = new Stack<GameUIState>();
	
	private BitmapFont font;
	private LabelStyle style;
	private NinePatch labelBackground;
	private Skin labelSkin;
	private Label goldLabel;
	private Label xpLabel;

	
	public static UIManager getInstance() {
	      if(instance == null) {
	         instance = new UIManager();
	      }
	      return instance;
	   }
	
	public ArrayList<DrawableObject> getDOs() {
		return DOs;
	}

	public void setDOs(ArrayList<DrawableObject> dOs) {
		DOs = dOs;
	}

	public UIManager(){
		this.initializeDraws();
	}
	
	public void initializeDraws(){
		drawables.add(new GroundDraw(0,0));
		drawables.add(new GrassDraw(270f , Game.GROUND_HEIGHT - 10, 50, 50));
		
		font = new BitmapFont(false);
		style = new LabelStyle(font, Color.MAGENTA);
		labelBackground = new NinePatch(Textures.GROUND, 10, 100, 10, 10);
		labelSkin = new Skin();
		labelSkin.add("background", labelBackground);
		style.background = labelSkin.getDrawable("background");
		
		goldLabel = new Label("Gold: ", style);
        xpLabel = new Label("Experience: ", style);
		goldLabel.setPosition(15, 750);
		xpLabel.setPosition(15, 700);
		
		
		
		State.add(State.push(GameUIState.DEFAULT));
		this.updateButtons();
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
				if(player.getGold()<AntiaircraftUnit.getCost(player))
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
				if(player.getGold()<FlyingUnit.getCost(player))
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
			this.buttons.add(new Buttons.UpgradeMeleeUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.UpgradeMeleeUnitButton(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
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
	
	public void clearDraws(){
		this.drawables.clear();
	}
	
	public void drawTextures(){
		for(Drawable draw:this.drawables){
			this.drawTexture(draw);
		}
		goldLabel.setText("Gold: " + WorldManager.getInstance().getPlayer().getGold().toString());
		goldLabel.draw(SB, 1);
		//font.draw(SB, "COBO", 200, 500);
		xpLabel.setText("XP: " + WorldManager.getInstance().getPlayer().getExp().toString());
		xpLabel.draw(SB, 1);
	}
	
	private void drawTexture(Drawable draw){
		
		//System.out.println(draw.getClass().getSimpleName() + " " + draw.getxPos() + " " + draw.getScreenHeight() + " " + draw.getSpriteHeight());
		
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
		for(DrawableObject DO: DOs){
			this.drawObject(DO);
		}
	}
	
	private void drawObject(DrawableObject DO){
		for(Drawable draw:DO.getDraws()){
			this.drawTexture(draw);
		}
	}
}

package UserInterface;

import java.util.ArrayList;
import java.util.Stack;

import Buttons.Button;
import Draws.Drawable;
import Draws.GroundDraw;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIManager {
	
	private static UIManager instance = null;
	
	private final int BUTTON_HEIGHT = 150;
	private final int BUTTON_INITIAL_X = 200;
	private final int BUTTON_SEPARATION = 150;
	private SpriteBatch SB;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private Stack<UIState> State = new Stack<UIState>();
	
	public static UIManager getInstance() {
	      if(instance == null) {
	         instance = new UIManager();
	      }
	      return instance;
	   }
	
	public UIManager(){
		drawables.add(new GroundDraw(0,0));
		State.add(State.push(UIState.DEFAULT));
	}
	
	public void setSpriteBatch(SpriteBatch SB){
		this.SB = SB;
	}
	
	public void pushState(UIState state){
		this.State.push(state);
	}
	
	public void popState(){
		this.State.pop();
	}
	
	public void updateButtons(){
		int count = 0;
		this.buttons.clear();
		switch(State.peek()){
		case DEFAULT:
			this.buttons.add(new Buttons.CreateUnit(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.Tower(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.Upgrade(this.BUTTON_INITIAL_X + count++ *this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case CREATE_UNIT:
			this.buttons.add(new Buttons.CreateRangedUnit(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.CreateFlyingUnit(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			this.buttons.add(new Buttons.CreateAntiaircraftUnit(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case TERRAIN_UNITS:
			break;
		case TOWER:
			this.buttons.add(new Buttons.CreateBasicTower(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		case FLYING_UNITS:
			break;
		case UNDERGROUND_UNITS:
			break;
		case UPGRADES:
			this.buttons.add(new Buttons.UpgradeMeleeUnitDamage(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
			break;
		}
		if(!State.peek().equals(UIState.DEFAULT)){
			this.buttons.add(new Buttons.Back(this.BUTTON_INITIAL_X + count++ * this.BUTTON_SEPARATION, this.BUTTON_HEIGHT));
		}
	}

	public ArrayList<Drawable> getDraws(){
		return this.drawables;
	}
	
	public ArrayList<Button> getButtons() {
		return this.buttons;
	}
	
	public void drawTextures(){
		for(Drawable draw:this.drawables){
			if (draw.getClass().getSimpleName().equals("BasicTowerDraw")){
			}
			SB.draw(draw.getTexture(), (float)draw.getxPos(), (float) draw.getyPos(), draw.getScreenWidth(), 
					draw.getScreenHeight(), 0, 0, draw.getSpriteWidth(), draw.getSpriteHeight(), false, false);
		}
	}
	
	public void drawButtons(){
		for(Button button: buttons){
			this.drawButton(button);
		}
	}
	
	private void drawButton(Button button){
/*
		System.out.println(button.getClass().getSimpleName());
		
		System.out.println(button.getDraw().getxPos());
		System.out.println(button.getDraw().getyPos());
		
		System.out.println(button.getDraw().getTexture().getHeight());
		System.out.println(button.getDraw().getTexture().getWidth());		
		
		System.out.println(button.getDraw().getScreenHeight());
		System.out.println(button.getDraw().getScreenWidth());		
	*/		
		SB.draw(button.getDraw().getTexture(), (float)button.getDraw().getxPos(), (float)button.getDraw().getyPos(), 
				button.getDraw().getScreenHeight(), button.getDraw().getScreenWidth());
	
	}
}

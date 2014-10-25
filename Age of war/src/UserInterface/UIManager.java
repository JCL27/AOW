package UserInterface;

import java.util.ArrayList;

import Buttons.Button;
import Buttons.CreateMeleeUnit;
import Buttons.UpgradeMeleeUnitDamage;
import Draws.Drawable;
import Draws.GroundDraw;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIManager {
	
	private static UIManager instance = null;
	
	private SpriteBatch SB;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private UIState State = UIState.DEFAULT;
	
	public static UIManager getInstance() {
	      if(instance == null) {
	         instance = new UIManager();
	      }
	      return instance;
	   }
	
	public UIManager(){
		drawables.add(new GroundDraw(0,0));
	}
	
	public void setSpriteBatch(SpriteBatch SB){
		this.SB = SB;
	}
	
	public void setState(UIState State){
		this.State = State;
	}
	
	public void updateButtons(){
		int count = 0;
		this.buttons.clear();
		switch(State){
		case DEFAULT: 	
			this.buttons.add(new UpgradeMeleeUnitDamage(200 + count++ * 300, 500));
			this.buttons.add(new CreateMeleeUnit(200 + count++ * 300, 500));
		case CREATE_UNIT:
		case TERRAIN_UNITS:
		case FLYING_UNITS:
		case UNDERGROUND_UNITS:
		case UPGRADES:
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

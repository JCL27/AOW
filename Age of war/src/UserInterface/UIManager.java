package UserInterface;

import java.util.ArrayList;

import Buttons.Button;
import Buttons.CreateMeleeUnit;
import Buttons.UpgradeMeleeUnitDamage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIManager {
	
	private static UIManager instance = null;
	
	private SpriteBatch SB;
	private ArrayList<Button> buttons = new ArrayList<Button>();

	public static UIManager getInstance() {
	      if(instance == null) {
	         instance = new UIManager();
	      }
	      return instance;
	   }
	
	private UIState State = UIState.DEFAULT;
	
	public UIManager(){
	}
	
	public void setSpriteBatch(SpriteBatch SB){
		this.SB = SB;
	}
	
	public void updateUI(){
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

	public ArrayList<Button> getButtons() {
		return this.buttons;
	}
	
	public void drawButtons(){
		for(Button button: buttons){
			this.drawButton(button);
		}
	}
	
	private void drawButton(Button button){
		SB.draw(button.getTexture(), (float)button.getX(),
				(float) button.getY(), button.getScreenWidth(),button.getScreenHeight());
	}
	
	
}

package ar.edu.itba.game.frontend.userinterface;

import java.util.ArrayList;

import ar.edu.itba.game.backend.logic.Game;
import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.frontend.buttons.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
/**
 * Gets the coordinates of the mouse and checks mouse clicks
 */
public class MyInputProcessor implements InputProcessor{
	
	@Override
	public boolean keyDown(int arg0) {
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		return false;
	}
	
	/**
	 * Checks whether the mouse is over a Label or a Button 
	 */
	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		
		/**
		 * Scale the X and Y positions according to the Game Height and Width 
		 */
		float scaledX = arg0 * (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth();
		float scaledY = Game.HEIGHT * Game.SCALE - arg1 * (Game.HEIGHT * Game.SCALE)/ Gdx.graphics.getHeight();
		
		/**
		 * Set displayable labels visibility
		 */
		if (scaledY>(GameStats.LABEL_UNITS_Y - 10) && scaledY<(GameStats.LABEL_UNITS_Y + 20) && 
				scaledX>GameStats.LABEL_RANGED_X && scaledX<(GameStats.LABEL_RANGED_X + GameStats.BACKGROUND_RIGHT_SIZE) ){
			UIManager.getInstance().setRangedLabelvisible(true);
		}
		else if (scaledY>(GameStats.LABEL_UNITS_Y -10) && scaledY<(GameStats.LABEL_UNITS_Y + 20) && 
				scaledX>GameStats.LABEL_MELEE_X && scaledX<(GameStats.LABEL_MELEE_X + GameStats.BACKGROUND_RIGHT_SIZE)){
			UIManager.getInstance().setMeleeLabelvisible(true);
		}
		else if (scaledY>(GameStats.LABEL_UNITS_Y -10) && scaledY<(GameStats.LABEL_UNITS_Y + 20) && 
				scaledX>GameStats.LABEL_ANTIAIRCRAFT_X && scaledX<(GameStats.LABEL_ANTIAIRCRAFT_X + GameStats.BACKGROUND_RIGHT_SIZE)){
			UIManager.getInstance().setAntiaircraftLabelvisible(true);
		}
		else if (scaledY>(GameStats.LABEL_UNITS_Y -10) && scaledY<(GameStats.LABEL_UNITS_Y + 20) && 
				scaledX>GameStats.LABEL_FLYING_X && scaledX<(GameStats.LABEL_FLYING_X + GameStats.BACKGROUND_RIGHT_SIZE)){
			UIManager.getInstance().setFlyingLabelvisible(true);
		}
		else{
			UIManager.getInstance().setFlyingLabelvisible(false);
			UIManager.getInstance().setMeleeLabelvisible(false);
			UIManager.getInstance().setRangedLabelvisible(false);
			UIManager.getInstance().setAntiaircraftLabelvisible(false);
		}	
		
		/**
		 * Set button's descriptions visibility
		 */
		for(Button button : UIManager.getInstance().getButtons()){
			if(button.isClicked(scaledX, scaledY)){
					button.setMessageVisibility(true);
			}
			else
				button.setMessageVisibility(false);
		}
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		return false;
	}
	
	/**
	 * Checks if a button is clicked and executes click over that button
	 */
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		ArrayList<Button> buttons = UIManager.getInstance().getButtons();
		float scaledX = arg0 * (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth();
		float scaledY = Game.HEIGHT * Game.SCALE - arg1 * (Game.HEIGHT * Game.SCALE)/ Gdx.graphics.getHeight();
		Button buttonClicked = null;
		//System.out.println("MyInput: " + scaledX + " " + scaledY);
		for(Button button: buttons){
			if(button.isClicked(scaledX, scaledY)){
				buttonClicked = button;
			}
		}
		if(buttonClicked!=null){
			buttonClicked.checkAndClick();
		}
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

}

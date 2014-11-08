package UserInterface;

import java.util.ArrayList;

import Buttons.Button;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor{
	
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		
		float scaledX = arg0 * (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth();
		float scaledY = Game.HEIGHT * Game.SCALE - arg1 * (Game.HEIGHT * Game.SCALE)/ Gdx.graphics.getHeight();
		
		if (scaledY>(GameStats.LABEL_UNITS_Y - 10) && scaledY<(GameStats.LABEL_UNITS_Y + 20) && 
				scaledX>GameStats.LABEL_RANGED_X && scaledX<(GameStats.LABEL_RANGED_X + GameStats.BACKGROUND_RIGHT) ){
			UIManager.getInstance().setRangedLabelvisible(true);
		}
		else if (scaledY>(GameStats.LABEL_UNITS_Y -10) && scaledY<(GameStats.LABEL_UNITS_Y + 20) && 
				scaledX>GameStats.LABEL_MELEE_X && scaledX<(GameStats.LABEL_MELEE_X + GameStats.BACKGROUND_RIGHT)){
			UIManager.getInstance().setMeleeLabelvisible(true);
		}
		else if (scaledY>(GameStats.LABEL_UNITS_Y -10) && scaledY<(GameStats.LABEL_UNITS_Y + 20) && 
				scaledX>GameStats.LABEL_ANTIAIRCRAFT_X && scaledX<(GameStats.LABEL_ANTIAIRCRAFT_X + GameStats.BACKGROUND_RIGHT)){
			UIManager.getInstance().setAntiaircraftLabelvisible(true);
		}
		else if (scaledY>(GameStats.LABEL_UNITS_Y -10) && scaledY<(GameStats.LABEL_UNITS_Y + 20) && 
				scaledX>GameStats.LABEL_FLYING_X && scaledX<(GameStats.LABEL_FLYING_X + GameStats.BACKGROUND_RIGHT)){
			UIManager.getInstance().setFlyingLabelvisible(true);
		}
		else{
			UIManager.getInstance().setFlyingLabelvisible(false);
			UIManager.getInstance().setMeleeLabelvisible(false);
			UIManager.getInstance().setRangedLabelvisible(false);
			UIManager.getInstance().setAntiaircraftLabelvisible(false);
		}	
		
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
		// TODO Auto-generated method stub
		return false;
	}

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}

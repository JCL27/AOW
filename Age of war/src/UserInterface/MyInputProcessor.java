package UserInterface;

import java.util.ArrayList;

import Buttons.Button;
import ar.edu.itba.game.Game;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		ArrayList<Button> buttons = UIManager.getInstance().getButtons();
		double scaledX = arg0 * (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth();
		double scaledY = Game.HEIGHT * Game.SCALE - arg1 * (Game.HEIGHT * Game.SCALE)/ Gdx.graphics.getHeight();
		Button buttonClicked = null;
		for(Button button: buttons){
			if(button.isClicked(scaledX, scaledY)){
				buttonClicked = button;
			}
		}
		if(buttonClicked!=null){
			buttonClicked.Click();
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

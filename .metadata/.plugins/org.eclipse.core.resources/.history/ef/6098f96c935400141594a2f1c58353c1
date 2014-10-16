package ar.edu.itba.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import exceptions.NullElementException;

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
		ArrayList<Element> elems = WorldManager.getInstance().getElements();
		double scaledX = arg0 * (Game.WIDTH * Game.SCALE)/Gdx.graphics.getWidth();
		double scaledY = Game.HEIGHT * Game.SCALE - arg1 * (Game.HEIGHT * Game.SCALE)/ Gdx.graphics.getHeight();
		Element elemClicked = null;
		for(Element elem: elems){
			if(elem.isContained(scaledX, scaledY)){
				elemClicked = elem;
			}
		}
		try{
		if(elemClicked == null){
			throw new NullElementException();
		}
		else
		{
			elemClicked.click();
		}
		}catch(NullElementException e){
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

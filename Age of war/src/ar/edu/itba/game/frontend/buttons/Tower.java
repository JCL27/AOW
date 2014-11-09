package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;
import ar.edu.itba.game.frontend.userinterface.GameUIState;
import ar.edu.itba.game.frontend.userinterface.UIManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tower extends Button{

	public Tower(float X, float Y){
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWERS_ICON);
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Tower", this.draw.getxPos(), this.draw.getyPos());
	}
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.TOWER);
		UIManager.getInstance().updateButtons();
	}
	
}

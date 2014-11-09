package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;
import ar.edu.itba.game.frontend.userinterface.GameUIState;
import ar.edu.itba.game.frontend.userinterface.UIManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class CreateUnit extends Button {

	public CreateUnit(float X, float Y){
		this.draw = new Icon(X, Y, 80, 80, Textures.CREATE_UNITS_ICON);
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Units", this.draw.getxPos(), this.draw.getyPos());
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().pushState(GameUIState.CREATE_UNIT);
		UIManager.getInstance().updateButtons();
	}
}

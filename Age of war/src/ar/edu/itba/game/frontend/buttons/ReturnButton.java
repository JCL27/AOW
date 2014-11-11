package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.frontend.draws.ArrowDraw;
import ar.edu.itba.game.frontend.userinterface.UIManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ReturnButton extends Button{

	public ReturnButton(float X, float Y) {
		this.draw = new ArrowDraw(X, Y);
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Go back", this.draw.getxPos(), this.draw.getyPos());
	}
	
	@Override
	public void Click() {
		UIManager.getInstance().popState();
		UIManager.getInstance().updateButtons();
	}
}

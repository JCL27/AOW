package ar.edu.itba.game.frontend.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.RangedUnit;
import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;

public class CreateRangedUnitButton extends Button {

	public CreateRangedUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.RANGED_UNIT_ICON, Textures.DARK_RANGED_UNIT_ICON);
	}

	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if(player.getGold()<RangedUnit.getCost(player)){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Buy Range Unit", this.draw.getxPos(), this.draw.getyPos());
	}

	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().buyUnit(RangedUnit.class);	
	}
	
	
	

}

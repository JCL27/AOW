package ar.edu.itba.game.frontend.buttons;

import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.backend.units.UnitType;
import ar.edu.itba.game.backend.units.UnitsLevels;
import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CreateFlyingUnitButton extends Button {


	public CreateFlyingUnitButton(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.FLYING_UNIT_ICON, Textures.DARK_FLYING_UNIT_ICON);
	}

	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();	
		if(player.getGold()<UnitsLevels.getInstance().getCost(player, UnitType.FLYING_UNIT) || !UnitsLevels.getInstance().isPlayerFlyingUnitAvailable()){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Buy Flying Unit", this.draw.getxPos(), this.draw.getyPos());
	}	
	
	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().buyUnit(UnitType.FLYING_UNIT);
	}

}

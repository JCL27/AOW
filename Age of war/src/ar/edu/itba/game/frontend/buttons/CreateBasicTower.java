package ar.edu.itba.game.frontend.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.game.backend.logic.GameStats;
import ar.edu.itba.game.backend.logic.Player;
import ar.edu.itba.game.backend.logic.WorldManager;
import ar.edu.itba.game.frontend.draws.Icon;
import ar.edu.itba.game.frontend.draws.Textures;

public class CreateBasicTower extends Button {
	
	public CreateBasicTower(float X, float Y) {
		this.draw = new Icon(X, Y, 80, 80, Textures.TOWER_RANGE_UPGRADE_ICON, Textures.DARK_TOWER_RANGE_UPGRADE_ICON);
	}

	public boolean checkAvailability(){
		Player player = WorldManager.getInstance().getPlayer();
		if((player.getGold()<GameStats.TOWER_COST) || (player.getTower()!=null)){
			this.getDraw().setDisabled();
			return false;
		}
		this.getDraw().setEnabled();
		return true;
	}
	
	public void showMessage(SpriteBatch SB){
		this.font.draw(SB, "Buy Tower", this.draw.getxPos(), this.draw.getyPos());
	}

	@Override
	public void Click() {
		WorldManager.getInstance().getPlayer().buyTower();
		//WorldManager.getInstance().getplayerAI().CreateTower();
	}
	

}

package Buttons;

import Draws.Icon;
import Draws.Textures;
import Units.MeleeUnit;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.WorldManager;

public class CreateMeleeUnit extends Button{
	
		public CreateMeleeUnit(float X, float Y) {
			this.draw = new Icon(X, Y, 80, 80, Textures.MELEE_UNIT_ICON, Textures.DARK_MELEE_UNIT_ICON);
		}
		
		public boolean checkAvailability(){
			Player player = WorldManager.getInstance().getPlayer();
			if(player.getGold()<MeleeUnit.getCost(player)){
				this.getDraw().setDisabled();
				return false;
			}
			this.getDraw().setEnabled();
			return true;
		}

		
	@Override
	public void Click() {

		//WorldManager.getInstance().getPlayer().createUnit(MeleeUnit.class);
		WorldManager.getInstance().getPlayer().buyUnit(MeleeUnit.class);
		//WorldManager.getInstance().getplayerAI().buyUnit(MeleeUnit.class);


	}
}

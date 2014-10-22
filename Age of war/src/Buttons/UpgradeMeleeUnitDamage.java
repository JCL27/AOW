package Buttons;

import Draws.BigGreenButtonDraw;
import UserInterface.Textures;
import ar.edu.itba.game.MeleeUnit;
import ar.edu.itba.game.Upgrades;
import ar.edu.itba.game.WorldManager;

public class UpgradeMeleeUnitDamage extends Button{
	public UpgradeMeleeUnitDamage(float X, float Y) {
		this.draw = new BigGreenButtonDraw(X, Y);
	}

	public void Click(){
		Upgrades.getInstance().UpgradeMeleeUnitDamage(WorldManager.getInstance().getPlayer());
		System.out.println(MeleeUnit.getPlayerUS().getDamageIndex());
	}
}

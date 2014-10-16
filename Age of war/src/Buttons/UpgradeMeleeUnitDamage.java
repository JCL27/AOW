package Buttons;

import UserInterface.Textures;
import ar.edu.itba.game.MeleeUnit;
import ar.edu.itba.game.Upgrades;
import ar.edu.itba.game.WorldManager;

public class UpgradeMeleeUnitDamage extends Button{
	public UpgradeMeleeUnitDamage(float X, float Y) {
		super(Textures.BUTTON, X, Y, 3);
		// TODO Auto-generated constructor stub
	}

	public void Click(){
		Upgrades.getInstance().UpgradeMeleeUnitDamage(WorldManager.getInstance().getPlayer());
		System.out.println(MeleeUnit.getPlayerUS().getDamageIndex());
	}
}

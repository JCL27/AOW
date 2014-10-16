package Buttons;

import ar.edu.itba.game.MeleeUnit;
import ar.edu.itba.game.Upgrades;
import ar.edu.itba.game.WorldManager;

import com.badlogic.gdx.graphics.Texture;

public class UpgradeMeleeUnitDamage extends Button{
	public UpgradeMeleeUnitDamage(Texture texture, float X, float Y, int scale) {
		super(texture, X, Y, scale);
		// TODO Auto-generated constructor stub
	}

	public void Click(){
		Upgrades.getInstance().UpgradeMeleeUnitDamage(WorldManager.getInstance().getPlayer());
		System.out.println(MeleeUnit.getPlayerUS().getDamageIndex());
	}
}

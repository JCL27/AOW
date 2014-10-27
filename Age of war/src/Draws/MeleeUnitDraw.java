package Draws;

import ar.edu.itba.game.Player;

public class MeleeUnitDraw extends Drawable {
	
	//DRAWABLE: BORRAR EL CONSTRUCTOR CON 2 PARAM, QUE RECIBA PLAYER
	//BORRAR lefttexture y rightTexture, scale totalSprites de Drawable
	public MeleeUnitDraw(float xPos, float yPos, int screenHeight, int screenWidth, Player player){
		super(xPos, yPos, screenHeight, screenWidth);
		this.player = player;
		
	}
	
	
	@Override
	public int getScreenWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScreenHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

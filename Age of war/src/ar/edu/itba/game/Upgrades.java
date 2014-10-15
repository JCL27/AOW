package ar.edu.itba.game;

public class Upgrades {
	
	private static Upgrades instance = null;
	
	public static Upgrades getInstance() {
	      if(instance == null) {
	         instance = new Upgrades();
	      }
	      return instance;
	   }
	
	public void UpgradeMeleeUnitDamage(Player player){
		if(player.equals(WorldManager.getInstance().getPlayer())){
			MeleeUnit.getPlayerUS().upgradeDamage();
		}
	}
}

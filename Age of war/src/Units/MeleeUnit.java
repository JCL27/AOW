package Units;

import Observers.UnitObserver;
import ar.edu.itba.game.Element;
import ar.edu.itba.game.Game;
import ar.edu.itba.game.GameStats;
import ar.edu.itba.game.Player;
import ar.edu.itba.game.Side;
import ar.edu.itba.game.WorldManager;

public class MeleeUnit extends Unit {
		public MeleeUnit(Player player){
			this.player = player;
			this.objective = null;
			this.cooldown = 0;
			this.bounty = GameStats.MELEE_UNIT_BOUNTY;
			this.cost = GameStats.MELEE_UNIT_COST;
			this.exp = GameStats.MELEE_UNIT_EXP;
			
			if (this.player.equals(WorldManager.getInstance().getPlayer())){
				this.maxHp = (int) (GameStats.MELEE_UNIT_MAX_HP + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_MAX_HP_UPGRADE_RATE));
				this.hp = this.maxHp;
				this.attackSpeed = GameStats.MELEE_UNIT_ATTACK_SPEED + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_ATTACK_SPEED_UPGRADE_RATE);
				this.attackRange = (int) (GameStats.MELEE_UNIT_ATTACK_RANGE + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_ATTACK_RANGE_UPGRADE_RATE));
				this.damage = (int) (GameStats.MELEE_UNIT_DAMAGE + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_DAMAGE_UPGRADE_RATE));
				this.element = new Element(100, Game.GROUND_HEIGHT, 3, 0, GameStats.MELEE_UNIT_COL_BOX_HEIGHT, GameStats.MELEE_UNIT_COL_BOX_WIDTH, false);
				this.movementSpeed = (int) (GameStats.MELEE_UNIT_MOVEMENT_SPEED + Math.sqrt(playerUnitLevel * GameStats.MELEE_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
				this.dir = Side.LEFT;
			}else{
				this.maxHp = (int) (GameStats.MELEE_UNIT_MAX_HP + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_MAX_HP_UPGRADE_RATE));
				this.hp = this.maxHp;
				this.attackSpeed = GameStats.MELEE_UNIT_ATTACK_SPEED + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_ATTACK_SPEED_UPGRADE_RATE);
				this.attackRange = (int) (GameStats.MELEE_UNIT_ATTACK_RANGE + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_ATTACK_RANGE_UPGRADE_RATE));
				this.damage = (int) (GameStats.MELEE_UNIT_DAMAGE + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_DAMAGE_UPGRADE_RATE));
				this.element = new Element(1000, Game.GROUND_HEIGHT, 3, 0, GameStats.MELEE_UNIT_COL_BOX_HEIGHT, GameStats.MELEE_UNIT_COL_BOX_WIDTH, false);
				this.movementSpeed = (-1) * (int) (GameStats.MELEE_UNIT_MOVEMENT_SPEED + Math.sqrt(AIUnitLevel * GameStats.MELEE_UNIT_MOVEMENT_SPEED_UPGRADE_RATE));
				this.dir = Side.RIGHT;
			}
			
			this.type = GameStats.MELEE_UNIT_TYPE;
			this.observer = new UnitObserver(this);
			this.addObserver(this.observer);
			this.attackFlying = false;
			WorldManager.getInstance().getElements().add(this.element);
		}
}

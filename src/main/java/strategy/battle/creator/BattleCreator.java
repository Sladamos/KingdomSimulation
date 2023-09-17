package strategy.battle.creator;

import strategy.battle.Battle;
import strategy.kingdom.Kingdom;

public interface BattleCreator {
	Battle createBattle(Kingdom firstKingdom, Kingdom secondKingdom);
}

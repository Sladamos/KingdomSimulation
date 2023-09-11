package strategy.battle;

import strategy.kingdom.Kingdom;

public interface BattleCreator {
	Battle createBattle(Kingdom firstKingdom, Kingdom secondKingdom);
}

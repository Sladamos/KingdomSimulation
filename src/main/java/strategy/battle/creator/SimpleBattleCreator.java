package strategy.battle.creator;

import strategy.battle.Battle;
import strategy.battle.SimpleBattle;
import strategy.kingdom.Kingdom;
import strategy.message.notifier.MessagesNotifierImpl;

public class SimpleBattleCreator implements BattleCreator {
	@Override
	public Battle createBattle(Kingdom firstKingdom, Kingdom secondKingdom) {
		return new SimpleBattle(firstKingdom, secondKingdom, new MessagesNotifierImpl<>());
	}
}

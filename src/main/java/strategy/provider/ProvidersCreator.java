package strategy.provider;

import strategy.provider.battle.SpeakingBattleIdProvider;
import strategy.provider.kingdom.SpeakingKingdomIdProvider;

public interface ProvidersCreator {
	SpeakingBattleIdProvider createSpeakingBattleIdProvider();
	SpeakingKingdomIdProvider createSpeakingKingdomIdProvider();
}

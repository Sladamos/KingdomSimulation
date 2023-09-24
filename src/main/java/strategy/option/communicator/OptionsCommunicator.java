package strategy.option.communicator;

import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

public interface OptionsCommunicator {
    void addKingdomIdProvider(KingdomIdProvider speakingProvider);
    void addBattleIdProvider(BattleIdProvider speakingProvider);
}

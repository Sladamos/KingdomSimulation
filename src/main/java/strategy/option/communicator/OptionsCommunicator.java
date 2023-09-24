package strategy.option.communicator;

import strategy.provider.SpeakingProvider;

public interface OptionsCommunicator {
    void addKingdomIdProvider(SpeakingProvider speakingProvider);
    void addBattleIdProvider(SpeakingProvider speakingProvider);
}

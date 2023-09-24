package strategy.option.communicator;

import strategy.option.NamedOption;
import strategy.option.OptionsProvider;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

import java.util.Map;

public interface OptionsCommunicator {
    void addKingdomIdProvider(KingdomIdProvider speakingProvider);
    void addBattleIdProvider(BattleIdProvider speakingProvider);
    void addManagedOptionsProvider(OptionsProvider optionsProvider);
    void setManagedOptions(Map<String, NamedOption> managedOptions);
}

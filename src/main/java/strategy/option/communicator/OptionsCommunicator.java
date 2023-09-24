package strategy.option.communicator;

import strategy.buffer.Buffer;
import strategy.option.NamedOption;
import strategy.option.OptionsProvider;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

import java.util.Map;

public interface OptionsCommunicator {
    KingdomIdProvider getKingdomIdProvider();
    BattleIdProvider getBattleIdProvider();
    Buffer<String> getOptionsBuffer();
    void addManagedOptionsProvider(OptionsProvider optionsProvider);
    void setManagedOptions(Map<String, NamedOption> managedOptions);
}

package strategy.option.communicator;

import strategy.buffer.SwitchableBuffer;
import strategy.option.NamedOption;
import strategy.option.executioner.OptionsProvider;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

import java.util.Map;

public interface OptionsCommunicator {
    KingdomIdProvider getKingdomIdProvider();
    BattleIdProvider getBattleIdProvider();
    SwitchableBuffer<String> getOptionsBuffer();
    void addManagedOptionsProvider(OptionsProvider optionsProvider);
    void setManagedOptions(Map<String, NamedOption> managedOptions);
}

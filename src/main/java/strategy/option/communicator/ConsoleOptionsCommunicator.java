package strategy.option.communicator;

import strategy.error.CriticalAppError;
import strategy.option.NamedOption;
import strategy.option.OptionsProvider;
import strategy.option.message.OptionMessagesCreator;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

import java.util.Map;

public class ConsoleOptionsCommunicator implements OptionsCommunicator {

    private final OptionMessagesCreator optionMessagesCreator;

    private Map<String, NamedOption> managedOptions;

    public ConsoleOptionsCommunicator(OptionMessagesCreator optionMessagesCreator) {
        this.optionMessagesCreator = optionMessagesCreator;
    }

    @Override
    public void addKingdomIdProvider(KingdomIdProvider speakingProvider) {
        speakingProvider.addListener(this::generateMessageAboutKingdomsId);
    }

    @Override
    public void addBattleIdProvider(BattleIdProvider speakingProvider) {
        speakingProvider.addListener(this::generateMessageAboutBattlesId);
    }

    @Override
    public void addManagedOptionsProvider(OptionsProvider speakingProvider) {
        speakingProvider.addSelectOptionListener(this::generateMessageAboutPossibleOptions);
    }

    @Override
    public void setManagedOptions(Map<String, NamedOption> managedOptions) {
        this.managedOptions = managedOptions;
    }

    private void generateMessageAboutPossibleOptions() {
        if (managedOptions == null) {
            throw new CriticalAppError("Managed options haven't been set");
        }
        System.out.println(optionMessagesCreator.createMessageAboutOptions(managedOptions));
    }

    private void generateMessageAboutKingdomsId() {
        System.out.println(optionMessagesCreator.getCreatedKingdomsId());
    }

    private void generateMessageAboutBattlesId() {
        System.out.println(optionMessagesCreator.getCreatedBattlesId());
    }
}

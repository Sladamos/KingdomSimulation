package strategy.option.communicator;

import lombok.Getter;
import strategy.buffer.Buffer;
import strategy.buffer.BufferImpl;
import strategy.error.CriticalAppError;
import strategy.option.NamedOption;
import strategy.option.OptionsProvider;
import strategy.option.message.OptionMessagesCreator;
import strategy.provider.battle.SpeakingBattleIdProvider;
import strategy.provider.kingdom.SpeakingKingdomIdProvider;

import java.util.Map;

public class ConsoleOptionsCommunicator implements OptionsCommunicator {

    @Getter
    private final Buffer<String> optionsBuffer;

    @Getter
    private final SpeakingKingdomIdProvider kingdomIdProvider;

    @Getter
    private final SpeakingBattleIdProvider battleIdProvider;

    private final OptionMessagesCreator optionMessagesCreator;

    private Map<String, NamedOption> managedOptions;

    public ConsoleOptionsCommunicator(OptionMessagesCreator optionMessagesCreator, SpeakingBattleIdProvider battleIdProvider, SpeakingKingdomIdProvider kingdomIdProvider) {
        this.optionMessagesCreator = optionMessagesCreator;
        this.battleIdProvider = battleIdProvider;
        this.kingdomIdProvider = kingdomIdProvider;
        battleIdProvider.addListener(this::generateMessageAboutBattlesId);
        kingdomIdProvider.addListener(this::generateMessageAboutKingdomsId);
        optionsBuffer = new BufferImpl<>();
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

package strategy.option.communicator;

import lombok.Getter;
import strategy.buffer.SwitchableBuffer;
import strategy.message.JSONMessage;
import strategy.message.logging.FileLogger;
import strategy.message.logging.Logger;
import strategy.option.NamedOption;
import strategy.option.OptionsProvider;
import strategy.option.message.OptionMessagesCreator;
import strategy.provider.battle.SpeakingBattleIdProvider;
import strategy.provider.kingdom.SpeakingKingdomIdProvider;

import java.util.Map;

public class LoggingOptionsCommunicator implements OptionsCommunicator {

    @Getter
    private final SwitchableBuffer<String> optionsBuffer;

    @Getter
    private final SpeakingKingdomIdProvider kingdomIdProvider;

    @Getter
    private final SpeakingBattleIdProvider battleIdProvider;

    private final OptionMessagesCreator optionMessagesCreator;

    private final Logger logger;

    private Map<String, NamedOption> managedOptions;

    public LoggingOptionsCommunicator(OptionMessagesCreator optionMessagesCreator, SwitchableBuffer<String> optionsBuffer, SpeakingBattleIdProvider battleIdProvider, SpeakingKingdomIdProvider kingdomIdProvider) {
        this.optionMessagesCreator = optionMessagesCreator;
        this.battleIdProvider = battleIdProvider;
        this.kingdomIdProvider = kingdomIdProvider;
        this.optionsBuffer = optionsBuffer;
        battleIdProvider.addListener(this::logMessageAboutBattlesId);
        kingdomIdProvider.addListener(this::logMessageAboutKingdomsId);
        logger = new FileLogger();
    }

    @Override
    public void addManagedOptionsProvider(OptionsProvider optionsProvider) {
        optionsProvider.addSelectOptionListener(this::logMessageAboutOptions);
    }

    @Override
    public void setManagedOptions(Map<String, NamedOption> managedOptions) {
        this.managedOptions = managedOptions;
    }

    private void logMessageAboutKingdomsId() {
        JSONMessage message = optionMessagesCreator.getCreatedKingdomsId();
        logger.logMessage(message);
    }

    private void logMessageAboutBattlesId() {
        JSONMessage message = optionMessagesCreator.getCreatedBattlesId();
        logger.logMessage(message);
    }

    private void logMessageAboutOptions() {
        JSONMessage message = optionMessagesCreator.createMessageAboutOptions(managedOptions);
        logger.logMessage(message);
    }
}

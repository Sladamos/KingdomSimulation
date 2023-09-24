package strategy.option.communicator;

import strategy.message.JSONMessage;
import strategy.message.logging.FileLogger;
import strategy.message.logging.Logger;
import strategy.option.message.OptionMessagesCreator;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

public class LoggingOptionsCommunicator implements OptionsCommunicator {

    private final OptionMessagesCreator optionMessagesCreator;

    private final Logger logger;

    public LoggingOptionsCommunicator(OptionMessagesCreator optionMessagesCreator) {
        this.optionMessagesCreator = optionMessagesCreator;
        this.logger = new FileLogger();
    }

    @Override
    public void addKingdomIdProvider(KingdomIdProvider speakingProvider) {
        speakingProvider.addListener(this::logMessageAboutKingdomsId);
    }

    @Override
    public void addBattleIdProvider(BattleIdProvider speakingProvider) {
        speakingProvider.addListener(this::logMessageAboutBattlesId);
    }

    private void logMessageAboutKingdomsId() {
        JSONMessage message = optionMessagesCreator.getCreatedKingdomsId();
        logger.logMessage(message);
    }

    private void logMessageAboutBattlesId() {
        JSONMessage message = optionMessagesCreator.getCreatedBattlesId();
        logger.logMessage(message);
    }
}

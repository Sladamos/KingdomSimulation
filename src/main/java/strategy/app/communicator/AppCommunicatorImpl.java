package strategy.app.communicator;

import strategy.error.ErrorsReceiver;
import strategy.message.JSONMessage;
import strategy.message.StringMessage;
import strategy.message.logging.FileLogger;
import strategy.message.logging.Logger;
import strategy.message.receiver.MessagesReceiver;
import strategy.message.sender.MessagesSender;
import strategy.option.AppExitOption;

public class AppCommunicatorImpl implements AppCommunicator {

    private final MessagesReceiver<StringMessage> battleMessagesReceiver;

    private final ErrorsReceiver errorsReceiver;

    private final MessagesReceiver<JSONMessage> kingdomMessagesReceiver;

    private final Logger logger;

    public AppCommunicatorImpl(MessagesReceiver<StringMessage> battleMessagesReceiver,
                               ErrorsReceiver errorsReceiver,
                               MessagesReceiver<JSONMessage> kingdomMessagesReceiver) {
        this.battleMessagesReceiver = battleMessagesReceiver;
        this.errorsReceiver = errorsReceiver;
        this.kingdomMessagesReceiver = kingdomMessagesReceiver;
        this.logger = new FileLogger();
    }

    @Override
    public void bindErrorsSender(MessagesSender<JSONMessage> errorsSender) {
        errorsSender.addListener(this::receiveErrorMessage);
    }

    @Override
    public void bindBattleSender(MessagesSender<StringMessage> battleSender) {
        battleSender.addListener(this::receiveMessageFromBattle);
    }

    @Override
    public void bindKingdomSender(MessagesSender<JSONMessage> kingdomSender) {
        kingdomSender.addListener(this::receiveMessageFromKingdom);
    }

    @Override
    public void receiveErrorMessage(JSONMessage message) {
        logger.logMessage(message);
        errorsReceiver.accept(message);
    }

    @Override
    public void receiveMessageFromBattle(StringMessage message) {
        logger.logMessage(message);
        battleMessagesReceiver.accept(message);
    }

    @Override
    public void receiveMessageFromKingdom(JSONMessage message) {
        logger.logMessage(message);
        kingdomMessagesReceiver.accept(message);
    }

    @Override
    public void setAppExitOption(AppExitOption appExitOption) {
        errorsReceiver.setAppExitOption(appExitOption);
    }
}

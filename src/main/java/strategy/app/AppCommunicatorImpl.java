package strategy.app;

import strategy.message.JSONMessage;
import strategy.message.sender.MessagesSender;
import strategy.message.StringMessage;
import strategy.message.logging.ConsoleLogger;
import strategy.message.logging.Logger;
import strategy.message.receiver.MessagesReceiver;

public class AppCommunicatorImpl implements AppCommunicator {

    private final MessagesReceiver<StringMessage> battleMessagesReceiver;

    private final MessagesReceiver<StringMessage> errorMessagesReceiver;

    private final MessagesReceiver<JSONMessage> kingdomMessagesReceiver;

    private final Logger logger;

    public AppCommunicatorImpl(MessagesReceiver<StringMessage> battleMessagesReceiver,
                               MessagesReceiver<StringMessage> errorMessagesReceiver,
                               MessagesReceiver<JSONMessage> kingdomMessagesReceiver) {
        this.battleMessagesReceiver = battleMessagesReceiver;
        this.errorMessagesReceiver = errorMessagesReceiver;
        this.kingdomMessagesReceiver = kingdomMessagesReceiver;
        this.logger = new ConsoleLogger();
    }

    @Override
    public void bindErrorsSender(MessagesSender<StringMessage> errorsSender) {
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
    public void receiveErrorMessage(StringMessage message) {
        logger.logMessage(message);
        errorMessagesReceiver.accept(message);
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
}

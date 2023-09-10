package strategy.message;

import strategy.message.logging.Logger;
import strategy.message.messenger.Messenger;
import strategy.message.receiver.MessagesReceiver;

import java.util.Map;

public class AppCommunicator {
    private MessagesReceiver<StringMessage> battleMessagesReceiver;
    private MessagesReceiver<StringMessage> errorMessagesReceiver;
    private Map<String, Messenger> kingdomsMessengers;
    private Logger logger;

    private void receiveMessage(StringMessage message) {
        logger.logMessage(message);
    }

    private void receiveMessage(JSONMessage message) {
        logger.logMessage(message);
    }
}

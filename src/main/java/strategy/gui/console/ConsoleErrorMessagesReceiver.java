package strategy.gui.console;

import lombok.Setter;
import strategy.error.ErrorsReceiver;
import strategy.message.JSONMessage;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.message.receiver.MessagesReceiver;
import strategy.option.AppExitOption;

public class ConsoleErrorMessagesReceiver implements ErrorsReceiver {

    private final MessagesReceiver<JSONMessage> messagesReceiver;

    @Setter
    private AppExitOption appExitOption;

    public ConsoleErrorMessagesReceiver() {
        messagesReceiver = new ConsoleMessagesReceiver<>();
    }

    @Override
    public void accept(JSONMessage message) {
        messagesReceiver.accept(message);
        if(message.getContent().getString("type").equals("critical")) {
            appExitOption.execute();
        }
    }
}

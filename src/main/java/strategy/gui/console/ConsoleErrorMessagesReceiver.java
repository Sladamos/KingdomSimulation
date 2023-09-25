package strategy.gui.console;

import lombok.Setter;
import strategy.message.JSONMessage;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.message.receiver.MessagesReceiver;

public class ConsoleErrorMessagesReceiver implements MessagesReceiver<JSONMessage> {

    private final MessagesReceiver<JSONMessage> messagesReceiver;

    @Setter
    private Runnable disableApp;

    public ConsoleErrorMessagesReceiver(Runnable disableGUI) {
        this.disableApp = disableGUI;
        messagesReceiver = new ConsoleMessagesReceiver<>();
    }

    public ConsoleErrorMessagesReceiver() {
        this(null);
    }

    @Override
    public void accept(JSONMessage message) {
        messagesReceiver.accept(message);
        if(message.getContent().getString("type").equals("critical")) {
            disableApp.run();
        }
    }
}

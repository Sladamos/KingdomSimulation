package strategy.gui.console;

import strategy.message.JSONMessage;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.message.receiver.MessagesReceiver;

public class ConsoleErrorMessagesReceiver implements MessagesReceiver<JSONMessage> {

    private final MessagesReceiver<JSONMessage> messagesReceiver;

    private final Runnable disableGUI;

    public ConsoleErrorMessagesReceiver(Runnable disableGUI) {
        this.disableGUI = disableGUI;
        messagesReceiver = new ConsoleMessagesReceiver<>();
    }

    @Override
    public void accept(JSONMessage message) {
        messagesReceiver.accept(message);
        if(message.getContent().getString("type").equals("critical")) {
            disableGUI.run();
        }
    }
}

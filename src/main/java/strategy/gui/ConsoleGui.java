package strategy.gui;

import strategy.app.AppCommunicator;
import strategy.app.AppCommunicatorImpl;
import strategy.message.receiver.ConsoleMessagesReceiver;

public class ConsoleGui implements GUI {

    private final AppCommunicator appCommunicator;

    public ConsoleGui() {
        appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleMessagesReceiver<>(),
                new ConsoleMessagesReceiver<>());
    }

    @Override
    public AppCommunicator getAppCommunicator() {
        return appCommunicator;
    }
}

package strategy.gui;

import strategy.app.AppCommunicator;
import strategy.app.AppCommunicatorImpl;
import strategy.app.AppInputHandler;
import strategy.gui.console.ConsoleGUIInputHandler;
import strategy.message.receiver.ConsoleMessagesReceiver;

public class ConsoleGUI implements GUI {

    private final AppCommunicator appCommunicator;

    private final AppInputHandler appInputHandler;

    public ConsoleGUI() {
        appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleMessagesReceiver<>(),
                new ConsoleMessagesReceiver<>());
        appInputHandler = new ConsoleGUIInputHandler();
    }

    @Override
    public AppCommunicator getAppCommunicator() {
        return appCommunicator;
    }

    @Override
    public AppInputHandler getAppInputHandler() {
        return appInputHandler;
    }
}

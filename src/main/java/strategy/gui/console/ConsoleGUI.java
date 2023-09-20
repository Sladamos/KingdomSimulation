package strategy.gui.console;

import strategy.app.AppCommunicator;
import strategy.app.AppCommunicatorImpl;
import strategy.app.AppInputHandlerManager;
import strategy.gui.GUI;
import strategy.message.receiver.ConsoleMessagesReceiver;

public class ConsoleGUI implements GUI {

    private final AppCommunicator appCommunicator;

    private final AppInputHandlerManager appInputHandlerManager;

    public ConsoleGUI() {
        appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleErrorMessagesReceiver(this::onGUIDisabled),
                new ConsoleMessagesReceiver<>());
        appInputHandlerManager = new GUIInputHandlerManager();
    }

    @Override
    public AppCommunicator getAppCommunicator() {
        return appCommunicator;
    }

    @Override
    public AppInputHandlerManager getAppInputHandler() {
        return appInputHandlerManager;
    }

    private void onGUIDisabled() {
        appInputHandlerManager.disableInputHandling();
    }
}
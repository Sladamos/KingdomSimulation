package strategy.gui.console;

import strategy.app.AppCommunicator;
import strategy.app.AppCommunicatorImpl;
import strategy.app.AppInputHandlerManager;
import strategy.app.options.AppOptionsManager;
import strategy.app.options.AppOptionsManagerImpl;
import strategy.app.options.ModificableAppOptionsManager;
import strategy.buffor.Buffer;
import strategy.buffor.BufferImpl;
import strategy.gui.GUI;
import strategy.gui.GUIInputHandlerManager;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.option.kingdom.KingdomLaunchedOptionImpl;

public class ConsoleGUI implements GUI {

    private final AppCommunicator appCommunicator;

    private final AppInputHandlerManager appInputHandlerManager;


    private final ModificableAppOptionsManager modificableAppOptionsManager;

    public ConsoleGUI() {
        Buffer<String> optionsBuffer = new BufferImpl<>();
        appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleErrorMessagesReceiver(this::onGUIDisabled),
                new ConsoleMessagesReceiver<>());
        appInputHandlerManager = new GUIInputHandlerManager(new ConsoleGUIInputHandler(), optionsBuffer);
        modificableAppOptionsManager = new AppOptionsManagerImpl();
        modificableAppOptionsManager.setKingdomLaunchedOption(new KingdomLaunchedOptionImpl(new BufferKingdomIdProvider(optionsBuffer)));
    }

    @Override
    public AppCommunicator getAppCommunicator() {
        return appCommunicator;
    }

    @Override
    public AppInputHandlerManager getAppInputHandler() {
        return appInputHandlerManager;
    }

    @Override
    public AppOptionsManager getAppOptionsManager() {
        return modificableAppOptionsManager;
    }

    private void onGUIDisabled() {
        appInputHandlerManager.disableInputHandling();
    }
}

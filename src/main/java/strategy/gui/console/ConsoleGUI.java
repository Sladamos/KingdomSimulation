package strategy.gui.console;

import strategy.app.AppCommunicator;
import strategy.app.AppCommunicatorImpl;
import strategy.app.AppInputHandlerManager;
import strategy.app.options.AppOptionsManager;
import strategy.app.options.AppOptionsManagerImpl;
import strategy.app.options.ModificableAppOptionsManager;
import strategy.buffer.Buffer;
import strategy.buffer.BufferImpl;
import strategy.buffer.SwitchableBuffer;
import strategy.gui.GUI;
import strategy.gui.GUIInputHandlerManager;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.option.Option;
import strategy.option.OptionsExecutioner;
import strategy.option.OptionsExecutionerImpl;
import strategy.option.kingdom.BufferKingdomIdProvider;
import strategy.option.kingdom.KingdomIdProvider;
import strategy.option.kingdom.KingdomLaunchedOption;
import strategy.option.kingdom.KingdomLaunchedOptionImpl;

import java.util.Map;

public class ConsoleGUI implements GUI {

    private final AppCommunicator appCommunicator;

    private final AppInputHandlerManager appInputHandlerManager;

    private final ModificableAppOptionsManager modificableAppOptionsManager;

    public ConsoleGUI() {
        SwitchableBuffer<String> optionsBuffer = new BufferImpl<>();
        appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleErrorMessagesReceiver(this::onGUIDisabled),
                new ConsoleMessagesReceiver<>());
        modificableAppOptionsManager = createOptionsManager(optionsBuffer);
        OptionsExecutioner optionsExecutioner = createOptionsExecutioner(optionsBuffer);
        appInputHandlerManager = new GUIInputHandlerManager(new ConsoleInputHandler(), optionsExecutioner);
        appInputHandlerManager.addInputHandledListener(optionsBuffer::addItem);
        optionsBuffer.enableAcceptingItems();
    }

    private OptionsExecutioner createOptionsExecutioner(Buffer<String> optionsBuffer) {
        Map<String, Option> managedOptions = modificableAppOptionsManager.getManagedOptions();
        return new OptionsExecutionerImpl(managedOptions, optionsBuffer);
    }

    private ModificableAppOptionsManager createOptionsManager(Buffer<String> optionsBuffer) {
        ModificableAppOptionsManager optionsManager = new AppOptionsManagerImpl();
        KingdomIdProvider kingdomIdProvider = new BufferKingdomIdProvider(optionsBuffer);
        KingdomLaunchedOption kingdomLaunchedOption = new KingdomLaunchedOptionImpl(kingdomIdProvider);
        optionsManager.setKingdomLaunchedOption(kingdomLaunchedOption);
        return optionsManager;
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

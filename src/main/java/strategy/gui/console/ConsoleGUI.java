package strategy.gui.console;

import lombok.Getter;
import strategy.app.AppCommunicator;
import strategy.app.AppCommunicatorImpl;
import strategy.app.AppController;
import strategy.app.options.AppOptionsManagerImpl;
import strategy.app.options.ModificableAppOptionsManager;
import strategy.buffer.Buffer;
import strategy.buffer.BufferImpl;
import strategy.buffer.SwitchableBuffer;
import strategy.gui.GUI;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.option.Option;
import strategy.option.OptionsExecutioner;
import strategy.option.OptionsExecutionerImpl;
import strategy.option.kingdom.*;

import java.util.Map;

public class ConsoleGUI implements GUI {

    @Getter
    private final AppCommunicator appCommunicator;

    @Getter
    private final AppController appController;

    @Getter
    private final ModificableAppOptionsManager appOptionsManager;

    public ConsoleGUI() {
        SwitchableBuffer<String> optionsBuffer = new BufferImpl<>();
        appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleErrorMessagesReceiver(this::onGUIDisabled),
                new ConsoleMessagesReceiver<>());
        appOptionsManager = createOptionsManager(optionsBuffer);
        OptionsExecutioner optionsExecutioner = createOptionsExecutioner(optionsBuffer);
        //appInputHandlerManager = new GUIInputHandlerManager(new ConsoleInputHandler(), optionsExecutioner);
        //appInputHandlerManager.addInputHandledListener(optionsBuffer::addItem);
        optionsBuffer.enableAcceptingItems();
        appController = null;
    }

    private OptionsExecutioner createOptionsExecutioner(Buffer<String> optionsBuffer) {
        Map<String, Option> managedOptions = appOptionsManager.getManagedOptions();
        return new OptionsExecutionerImpl(managedOptions, optionsBuffer);
    }

    private ModificableAppOptionsManager createOptionsManager(Buffer<String> optionsBuffer) {
        ModificableAppOptionsManager optionsManager = new AppOptionsManagerImpl();
        KingdomIdProvider kingdomIdProvider = new BufferKingdomIdProvider(optionsBuffer);
        KingdomLaunchedOption kingdomLaunchedOption = new KingdomLaunchedOptionImpl(kingdomIdProvider);
        KingdomStoppedOption kingdomStoppedOption = new KingdomStoppedOptionImpl(kingdomIdProvider);
        optionsManager.setKingdomLaunchedOption(kingdomLaunchedOption);
        optionsManager.setKingdomStoppedOption(kingdomStoppedOption);
        return optionsManager;
    }

    private void onGUIDisabled() {
        //TODO: disable buffor executioner and input handler
        // change app input handler to app controller
        //appInputHandlerManager.disableInputHandling();
    }
}

package strategy.gui.console;

import lombok.Getter;
import strategy.app.communicator.AppCommunicator;
import strategy.app.communicator.AppCommunicatorImpl;
import strategy.app.controller.AppController;
import strategy.app.inputhandller.AppInputHandler;
import strategy.app.inputhandller.ConsoleInputHandler;
import strategy.app.options.AppOptionsManagerImpl;
import strategy.app.options.ModificableAppOptionsManager;
import strategy.buffer.BufferImpl;
import strategy.buffer.SwitchableBuffer;
import strategy.app.controller.AppControllerImpl;
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

    private final OptionsExecutioner optionsExecutioner;

    private final SwitchableBuffer<String> optionsBuffer;

    public ConsoleGUI() {
        optionsBuffer = new BufferImpl<>();
        appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleErrorMessagesReceiver(this::onGUIDisabled),
                new ConsoleMessagesReceiver<>());
        appOptionsManager = createOptionsManager();
        optionsExecutioner = createOptionsExecutioner();
        appController = createAppController();
    }

    private AppController createAppController() {
        AppInputHandler inputHandler = new ConsoleInputHandler();
        inputHandler.addInputHandledListener(optionsBuffer::addItem);
        return new AppControllerImpl(inputHandler, optionsExecutioner, optionsBuffer);
    }

    private OptionsExecutioner createOptionsExecutioner() {
        Map<String, Option> managedOptions = appOptionsManager.getManagedOptions();
        return new OptionsExecutionerImpl(managedOptions, optionsBuffer);
    }

    private ModificableAppOptionsManager createOptionsManager() {
        ModificableAppOptionsManager optionsManager = new AppOptionsManagerImpl();
        KingdomIdProvider kingdomIdProvider = new BufferKingdomIdProvider(optionsBuffer);
        KingdomLaunchedOption kingdomLaunchedOption = new KingdomLaunchedOptionImpl(kingdomIdProvider);
        KingdomStoppedOption kingdomStoppedOption = new KingdomStoppedOptionImpl(kingdomIdProvider);
        optionsManager.setKingdomLaunchedOption(kingdomLaunchedOption);
        optionsManager.setKingdomStoppedOption(kingdomStoppedOption);
        optionsManager.setAppDisabledOption(this::onGUIDisabled);
        return optionsManager;
    }

    private void onGUIDisabled() {
        appController.disableExecutingOptions();
    }
}

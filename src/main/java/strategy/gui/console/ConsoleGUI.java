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
import strategy.option.*;
import strategy.option.battle.*;
import strategy.option.communicator.OptionsCommunicator;
import strategy.option.kingdom.*;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.battle.SpeakingBufferBattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;
import strategy.provider.kingdom.SpeakingBufferKingdomIdProvider;

import java.util.HashMap;
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

    private final OptionsCommunicator optionsCommunicator;

    private final KingdomIdProvider kingdomIdProvider;
    private final BattleIdProvider battleIdProvider;

    public ConsoleGUI(OptionsCommunicator optionsCommunicator) {
        this.optionsCommunicator = optionsCommunicator;
        optionsBuffer = new BufferImpl<>();
        kingdomIdProvider = new SpeakingBufferKingdomIdProvider(optionsBuffer);
        battleIdProvider = new SpeakingBufferBattleIdProvider(optionsBuffer);
        appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleErrorMessagesReceiver(this::onGUIDisabled),
                new ConsoleMessagesReceiver<>());
        appOptionsManager = createOptionsManager();
        optionsExecutioner = createOptionsExecutioner();
        appController = createAppController();
        bindOptionsCommunicator();
    }

    private void bindOptionsCommunicator() {
        optionsCommunicator.addBattleIdProvider(battleIdProvider);
        optionsCommunicator.addKingdomIdProvider(kingdomIdProvider);
        optionsCommunicator.addManagedOptionsProvider(optionsExecutioner);
        Map<String, NamedOption> managedOptions = appOptionsManager.getManagedOptions();
        optionsCommunicator.setManagedOptions(managedOptions);
    }

    private AppController createAppController() {
        AppInputHandler inputHandler = new ConsoleInputHandler();
        inputHandler.addInputHandledListener(optionsBuffer::addItem);
        return new AppControllerImpl(inputHandler, optionsExecutioner, optionsBuffer);
    }

    private OptionsExecutioner createOptionsExecutioner() {
        Map<String, Option> managedOptions = new HashMap<>(appOptionsManager.getManagedOptions());
        return new OptionsExecutionerImpl(managedOptions, optionsBuffer);
    }

    private ModificableAppOptionsManager createOptionsManager() {
        ModificableAppOptionsManager optionsManager = new AppOptionsManagerImpl();
        KingdomLaunchedOption kingdomLaunchedOption = new KingdomLaunchedOptionImpl(kingdomIdProvider);
        KingdomStoppedOption kingdomStoppedOption = new KingdomStoppedOptionImpl(kingdomIdProvider);
        BattleLaunchedOption battleLaunchedOption = new BattleLaunchedOptionImpl(kingdomIdProvider);
        BattleStoppedOption battleStoppedOption = new BattleStoppedOptionImpl(battleIdProvider);
        AppExitOption exitOption = new AppExitOptionImpl(this::onGUIDisabled);
        optionsManager.setKingdomLaunchedOption(kingdomLaunchedOption);
        optionsManager.setKingdomStoppedOption(kingdomStoppedOption);
        optionsManager.setBattleLaunchedOption(battleLaunchedOption);
        optionsManager.setBattleStoppedOption(battleStoppedOption);
        optionsManager.setAppDisabledOption(exitOption);
        return optionsManager;
    }

    private void onGUIDisabled() {
        appController.disableExecutingOptions();
    }
}

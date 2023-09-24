package strategy.gui.console;

import lombok.Getter;
import strategy.app.communicator.AppCommunicator;
import strategy.app.communicator.AppCommunicatorImpl;
import strategy.app.controller.AppController;
import strategy.app.controller.AppControllerImpl;
import strategy.app.inputhandller.AppInputHandler;
import strategy.app.inputhandller.ConsoleInputHandler;
import strategy.app.options.AppOptionsManagerImpl;
import strategy.app.options.ModificableAppOptionsManager;
import strategy.buffer.SwitchableBuffer;
import strategy.gui.GUI;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.option.*;
import strategy.option.battle.BattleLaunchedOption;
import strategy.option.battle.BattleLaunchedOptionImpl;
import strategy.option.battle.BattleStoppedOption;
import strategy.option.battle.BattleStoppedOptionImpl;
import strategy.option.communicator.OptionsCommunicator;
import strategy.option.kingdom.KingdomLaunchedOption;
import strategy.option.kingdom.KingdomLaunchedOptionImpl;
import strategy.option.kingdom.KingdomStoppedOption;
import strategy.option.kingdom.KingdomStoppedOptionImpl;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

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

    private final OptionsCommunicator optionsCommunicator;

    private final SwitchableBuffer<String> optionsBuffer;

    public ConsoleGUI(OptionsCommunicator optionsCommunicator) {
        this.optionsCommunicator = optionsCommunicator;
        optionsBuffer = optionsCommunicator.getOptionsBuffer();
        appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleErrorMessagesReceiver(this::onGUIDisabled),
                new ConsoleMessagesReceiver<>());
        appOptionsManager = createOptionsManager();
        optionsExecutioner = createOptionsExecutioner();
        appController = createAppController();
        setOptionsCommunicatorOptions();
    }

    private void setOptionsCommunicatorOptions() {
        Map<String, NamedOption> managedOptions = appOptionsManager.getManagedOptions();
        optionsCommunicator.setManagedOptions(managedOptions);
        optionsCommunicator.addManagedOptionsProvider(optionsExecutioner);
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
        KingdomIdProvider kingdomIdProvider = optionsCommunicator.getKingdomIdProvider();
        BattleIdProvider battleIdProvider = optionsCommunicator.getBattleIdProvider();
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

package strategy.gui.console;

import lombok.Getter;
import strategy.app.communicator.AppCommunicator;
import strategy.app.controller.AppController;
import strategy.app.controller.AppControllerImpl;
import strategy.app.inputhandller.AppInputHandler;
import strategy.app.options.ModificableAppOptionsManager;
import strategy.buffer.SwitchableBuffer;
import strategy.gui.GUI;
import strategy.option.AppExitOption;
import strategy.option.AppExitOptionImpl;
import strategy.option.NamedOption;
import strategy.option.Option;
import strategy.option.communicator.OptionsCommunicator;
import strategy.option.creator.OptionsManagerCreator;
import strategy.option.creator.OptionsManagerCreatorImpl;
import strategy.option.executioner.OptionsExecutioner;
import strategy.option.executioner.OptionsExecutionerImpl;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

import java.util.HashMap;
import java.util.Map;

public class GUIImpl implements GUI {

    @Getter
    private final AppCommunicator appCommunicator;

    @Getter
    private final AppController appController;

    @Getter
    private final ModificableAppOptionsManager appOptionsManager;

    private final OptionsExecutioner optionsExecutioner;

    private final OptionsCommunicator optionsCommunicator;

    private final SwitchableBuffer<String> optionsBuffer;

    public GUIImpl(OptionsCommunicator optionsCommunicator, AppInputHandler inputHandler, AppCommunicator appCommunicator) {
        this.optionsCommunicator = optionsCommunicator;
        this.appCommunicator = appCommunicator;
        optionsBuffer = optionsCommunicator.getOptionsBuffer();
        appOptionsManager = createOptionsManager();
        setAppExitOption();
        optionsExecutioner = createOptionsExecutioner();
        appController = createAppController(inputHandler);
        setOptionsCommunicatorOptions();
    }

    private void setOptionsCommunicatorOptions() {
        Map<String, NamedOption> managedOptions = appOptionsManager.getManagedOptions();
        optionsCommunicator.setManagedOptions(managedOptions);
        optionsCommunicator.addManagedOptionsProvider(optionsExecutioner);
    }

    private AppController createAppController(AppInputHandler inputHandler) {
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
        OptionsManagerCreator optionsManagerCreator = new OptionsManagerCreatorImpl();
	    return optionsManagerCreator.createDefaultAppOptionsManager(kingdomIdProvider, battleIdProvider);
    }

    private void setAppExitOption() {
        AppExitOption exitOption = new AppExitOptionImpl(this::onGUIDisabled);
        appOptionsManager.setAppExitOption(exitOption);
        appCommunicator.setAppExitOption(exitOption);
    }

    private void onGUIDisabled() {
        appController.disableExecutingOptions();
    }
}

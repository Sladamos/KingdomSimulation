package strategy.initializer.gui;

import strategy.app.communicator.AppCommunicator;
import strategy.app.communicator.AppCommunicatorCreator;
import strategy.app.communicator.AppCommunicatorCreatorImpl;
import strategy.app.inputhandller.AppInputHandler;
import strategy.app.inputhandller.AppInputHandlerCreator;
import strategy.app.inputhandller.AppInputHandlerCreatorImpl;
import strategy.config.GUIConfigParser;
import strategy.config.creator.ConfigCreator;
import strategy.config.creator.ConfigCreatorImpl;
import strategy.gui.GUI;
import strategy.gui.GUIConfig;
import strategy.gui.GUIType;
import strategy.gui.console.GUIImpl;
import strategy.json.JsonLoader;
import strategy.option.communicator.OptionsCommunicator;
import strategy.option.communicator.OptionsCommunicatorCreator;
import strategy.option.communicator.OptionsCommunicatorCreatorImpl;
import strategy.option.message.OptionMessagesCreator;

public class GUIInitializerImpl implements GUIInitializer {

    private final OptionsCommunicatorCreator optionsCommunicatorCreator;

    private final AppInputHandlerCreator inputHandlerCreator;

    private final AppCommunicatorCreator appCommunicatorCreator;

    public GUIInitializerImpl(OptionMessagesCreator messagesCreator) {
        optionsCommunicatorCreator = new OptionsCommunicatorCreatorImpl(messagesCreator);
        appCommunicatorCreator = new AppCommunicatorCreatorImpl();
        inputHandlerCreator = new AppInputHandlerCreatorImpl();
    }

    @Override
    public GUI initializeGUI(JsonLoader jsonLoader) {
        GUIConfig config = createGUIConfig(jsonLoader);
        GUIType guiType = config.getGuiType();
        OptionsCommunicator optionsCommunicator = optionsCommunicatorCreator.createOptionsCommunicator(guiType);
        AppInputHandler inputHandler = inputHandlerCreator.createAppInputHandler(guiType);
        AppCommunicator appCommunicator = appCommunicatorCreator.createAppCommunicator(guiType);
        return new GUIImpl(optionsCommunicator, inputHandler, appCommunicator);
    }

    private GUIConfig createGUIConfig(JsonLoader jsonLoader) {
        ConfigCreator<GUIConfig> creator = new ConfigCreatorImpl<>();
        return creator.createConfigFromJson(jsonLoader, new GUIConfigParser());
    }
}

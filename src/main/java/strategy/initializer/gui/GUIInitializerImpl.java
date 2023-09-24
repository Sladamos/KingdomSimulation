package strategy.initializer.gui;

import strategy.config.GUIConfigParser;
import strategy.config.creator.ConfigCreator;
import strategy.config.creator.ConfigCreatorImpl;
import strategy.gui.GUI;
import strategy.gui.GUIConfig;
import strategy.gui.GUIType;
import strategy.gui.console.ConsoleGUI;
import strategy.json.JsonLoader;
import strategy.option.communicator.OptionsCommunicator;
import strategy.option.communicator.OptionsCommunicatorCreator;
import strategy.option.communicator.OptionsCommunicatorCreatorImpl;
import strategy.option.message.OptionMessagesCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class GUIInitializerImpl implements GUIInitializer {

    private final Map<GUIType, Function<OptionsCommunicator, GUI>> guiCreators;

    private final OptionsCommunicatorCreator optionsCommunicatorCreator;

    public GUIInitializerImpl(OptionMessagesCreator messagesCreator) {
        optionsCommunicatorCreator = new OptionsCommunicatorCreatorImpl(messagesCreator);
        guiCreators = new HashMap<>();
        guiCreators.put(GUIType.CONSOLE, ConsoleGUI::new);
    }

    @Override
    public GUI initializeGUI(JsonLoader jsonLoader) {
        GUIConfig config = createGUIConfig(jsonLoader);
        GUIType guiType = config.getGuiType();
        OptionsCommunicator communicator = optionsCommunicatorCreator.createOptionsCommunicator(guiType);
        return guiCreators.get(guiType).apply(communicator);
    }

    private GUIConfig createGUIConfig(JsonLoader jsonLoader) {
        ConfigCreator<GUIConfig> creator = new ConfigCreatorImpl<>();
        return creator.createConfigFromJson(jsonLoader, new GUIConfigParser());
    }
}

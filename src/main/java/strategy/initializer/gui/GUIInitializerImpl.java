package strategy.initializer.gui;

import strategy.config.GUIConfigParser;
import strategy.config.creator.ConfigCreator;
import strategy.config.creator.ConfigCreatorImpl;
import strategy.error.ErrorHandler;
import strategy.gui.console.ConsoleGUI;
import strategy.gui.GUI;
import strategy.gui.GUIConfig;
import strategy.gui.GUIType;
import strategy.json.JsonLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GUIInitializerImpl implements GUIInitializer {

    private final Map<GUIType, Supplier<GUI>> guiCreators;

    public GUIInitializerImpl() {
        guiCreators = new HashMap<>();
        guiCreators.put(GUIType.CONSOLE, ConsoleGUI::new);
    }

    @Override
    public GUI initializeGUI(JsonLoader jsonLoader) {
        GUIConfig config = createGUIConfig(jsonLoader);
        GUIType guiType = config.getGuiType();
        return guiCreators.get(guiType).get();
    }

    private GUIConfig createGUIConfig(JsonLoader jsonLoader) {
        ConfigCreator<GUIConfig> creator = new ConfigCreatorImpl<>();
        return creator.createConfigFromJson(jsonLoader, new GUIConfigParser());
    }
}

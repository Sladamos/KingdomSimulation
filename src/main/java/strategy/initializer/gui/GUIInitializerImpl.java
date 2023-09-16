package strategy.initializer.gui;

import strategy.config.GUIConfigParser;
import strategy.gui.ConsoleGui;
import strategy.gui.GUI;
import strategy.gui.GUIConfig;
import strategy.gui.GUIType;
import strategy.json.JSON;
import strategy.json.JsonLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GUIInitializerImpl implements GUIInitializer {

    private final Map<GUIType, Supplier<GUI>> guiCreators;

    public GUIInitializerImpl() {
        guiCreators = new HashMap<>();
        guiCreators.put(GUIType.CONSOLE, ConsoleGui::new);
    }

    @Override
    public GUI initializeGUI(JsonLoader jsonLoader) {
        GUIConfig config = createGUIConfig(jsonLoader);
        GUIType guiType = config.getGuiType();
        return guiCreators.get(guiType).get();
    }

    private GUIConfig createGUIConfig(JsonLoader jsonLoader) {
        JSON config = jsonLoader.loadJson();
        GUIConfigParser guiConfigParser = new GUIConfigParser();
        return guiConfigParser.createConfig(config);
    }
}

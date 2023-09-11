package strategy.initializer.gui;

import strategy.gui.ConsoleGui;
import strategy.gui.GUI;
import strategy.gui.GUIType;

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
    public GUI initializeGUI() {
        //TODO:
        //  readGuiConfig
        //  create gui from config
        return guiCreators.get(GUIType.CONSOLE).get();
    }
}

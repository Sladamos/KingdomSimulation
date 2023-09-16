package strategy.initializer.gui;

import strategy.gui.GUI;
import strategy.json.JsonLoader;

public interface GUIInitializer {
    GUI initializeGUI(JsonLoader jsonLoader);
}

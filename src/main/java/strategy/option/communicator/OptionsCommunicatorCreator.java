package strategy.option.communicator;

import strategy.gui.GUIType;

public interface OptionsCommunicatorCreator {
    OptionsCommunicator createOptionsCommunicator(GUIType guiType);
}

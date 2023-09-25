package strategy.app.communicator;

import strategy.gui.GUIType;

public interface AppCommunicatorCreator {
	ExitableAppCommunicator createAppCommunicator(GUIType guiType);
}

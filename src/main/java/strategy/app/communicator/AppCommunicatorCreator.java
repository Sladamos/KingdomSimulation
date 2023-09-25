package strategy.app.communicator;

import strategy.gui.GUIType;

public interface AppCommunicatorCreator {
	AppCommunicator createAppCommunicator(GUIType guiType);
}

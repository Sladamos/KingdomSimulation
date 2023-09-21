package strategy.gui;

import strategy.app.AppCommunicator;
import strategy.app.AppInputHandlerManager;
import strategy.app.options.AppOptionsManager;

public interface GUI {
    AppCommunicator getAppCommunicator();
    AppInputHandlerManager getAppInputHandler();
	AppOptionsManager getAppOptionsManager();
}

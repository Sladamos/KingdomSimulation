package strategy.gui;

import strategy.app.communicator.AppCommunicator;
import strategy.app.controller.AppController;
import strategy.app.options.AppOptionsManager;

public interface GUI {
    AppCommunicator getAppCommunicator();
	AppOptionsManager getAppOptionsManager();
	AppController getAppController();
}

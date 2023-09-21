package strategy.gui;

import strategy.app.AppCommunicator;
import strategy.app.AppController;
import strategy.app.options.AppOptionsManager;

public interface GUI {
    AppCommunicator getAppCommunicator();
	AppOptionsManager getAppOptionsManager();
	AppController getAppController();
}

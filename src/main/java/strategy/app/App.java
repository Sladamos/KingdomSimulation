package strategy.app;

import strategy.app.communicator.AppCommunicator;
import strategy.app.controller.AppController;
import strategy.app.options.AppOptionsManager;

public interface App extends AppCommunicator, AppController, AppOptionsManager {
}

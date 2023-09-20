package strategy.gui;

import strategy.app.AppCommunicator;
import strategy.app.AppInputHandlerManager;

public interface GUI {
    AppCommunicator getAppCommunicator();
    AppInputHandlerManager getAppInputHandler();
}

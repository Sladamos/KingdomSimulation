package strategy.gui;

import strategy.app.AppCommunicator;
import strategy.app.AppInputHandler;

public interface GUI {
    AppCommunicator getAppCommunicator();
    AppInputHandler getAppInputHandler();
}

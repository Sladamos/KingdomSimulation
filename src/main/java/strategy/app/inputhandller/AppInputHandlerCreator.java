package strategy.app.inputhandller;

import strategy.gui.GUIType;

public interface AppInputHandlerCreator {
	AppInputHandler createAppInputHandler(GUIType guiType);
}

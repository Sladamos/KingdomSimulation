package strategy.app.inputhandller;

import strategy.error.CriticalAppError;
import strategy.gui.GUIType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AppInputHandlerCreatorImpl implements AppInputHandlerCreator {

	private final Map<GUIType, Supplier<AppInputHandler>> inputHandlers;

	public AppInputHandlerCreatorImpl() {
		inputHandlers = new HashMap<>();
		inputHandlers.put(GUIType.CONSOLE, ConsoleInputHandler::new);
	}

	@Override
	public AppInputHandler createAppInputHandler(GUIType guiType) {
		if(!inputHandlers.containsKey(guiType)) {
			throw new CriticalAppError("Incorrect gui type.");
		}
		return inputHandlers.get(guiType).get();
	}
}

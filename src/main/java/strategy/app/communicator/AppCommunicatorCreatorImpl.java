package strategy.app.communicator;

import strategy.error.CriticalAppError;
import strategy.gui.GUIType;
import strategy.gui.console.ConsoleErrorMessagesReceiver;
import strategy.message.receiver.ConsoleMessagesReceiver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AppCommunicatorCreatorImpl implements AppCommunicatorCreator {

	private final Map<GUIType, Supplier<ExitableAppCommunicator>> appCommunicators;

	public AppCommunicatorCreatorImpl() {
		appCommunicators = new HashMap<>();
		appCommunicators.put(GUIType.CONSOLE, () -> new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
				new ConsoleErrorMessagesReceiver(),
				new ConsoleMessagesReceiver<>()));
	}

	@Override
	public ExitableAppCommunicator createAppCommunicator(GUIType guiType) {
		if(!appCommunicators.containsKey(guiType)) {
			throw new CriticalAppError("Incorrect gui type.");
		}
		return appCommunicators.get(guiType).get();
	}
}

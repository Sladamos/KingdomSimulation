package strategy.app;

import java.util.function.Consumer;

public interface AppInputHandler {
	void disableInputHandling();
	void enableInputHandling();
	void addInputHandledListener(Consumer<String> listener);
}

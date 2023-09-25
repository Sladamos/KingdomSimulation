package strategy.app.inputhandller;

import java.util.function.Consumer;

public interface AppInputHandler extends Runnable {
	void disableInputHandling();
	void addInputHandledListener(Consumer<String> listener);
}

package strategy.app.inputhandller;

import strategy.error.BasicAppError;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleInputHandler implements AppInputHandler {

	private boolean isLaunched;

	private final OneArgEvent<String> inputHandled;

	public ConsoleInputHandler() {
		inputHandled = new OneArgEventImpl<>();
		isLaunched = false;
	}

	@Override
	public synchronized void disableInputHandling() {
		isLaunched = false;
	}

	@Override
	public void addInputHandledListener(Consumer<String> listener) {
		inputHandled.addListener(listener);
	}

	@Override
	public void run() {
		isLaunched = true;
		Scanner scanner = new Scanner(System.in);
		while (isLaunched) {
			waitForInputInScanner();
			handleInputFromScanner(scanner);
		}
	}

	private void waitForInputInScanner() {
		try {
			while (isLaunched && doesNotHaveInput()) {
				waitSomeTime();
			}
		} catch (IOException exception) {
			throw new BasicAppError("IOException handled in in input handler.");
		}
	}

	private boolean doesNotHaveInput() throws IOException {
		return System.in.available() == 0;
	}

	private void waitSomeTime() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			throw new BasicAppError("Input handler forced to stop");
		}
	}

	private void handleInputFromScanner(Scanner scanner) {
		if(isLaunched) {
			String input = scanner.nextLine();
			inputHandled.invoke(input);
		}
	}
}

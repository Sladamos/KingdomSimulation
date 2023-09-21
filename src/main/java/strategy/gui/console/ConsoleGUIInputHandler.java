package strategy.gui.console;

import strategy.app.AppInputHandler;
import strategy.error.BasicAppError;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.util.ProtectedThread;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleGUIInputHandler implements AppInputHandler {

	private boolean isLaunched;

	private final OneArgEvent<String> inputHandled;

	private final Thread inputHandlerThread;

	public ConsoleGUIInputHandler() {
		inputHandled = new OneArgEventImpl<>();
		isLaunched = false;
		inputHandlerThread = new ProtectedThread(this::run);
	}

	@Override
	public synchronized void disableInputHandling() {
		isLaunched = false;
	}

	@Override
	public synchronized void enableInputHandling() {
		isLaunched = true;
		if(!inputHandlerThread.isAlive()) {
			inputHandlerThread.start();
		}
	}

	@Override
	public void addInputHandledListener(Consumer<String> listener) {
		inputHandled.addListener(listener);
	}

	private void run() {
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
			String input = scanner.next();
			inputHandled.invoke(input);
		}
	}
}

package strategy.gui.console;

import strategy.app.AppInputHandler;
import strategy.error.BasicAppError;
import strategy.error.ErrorHandler;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleGUIInputHandler implements Runnable, AppInputHandler {
	private boolean isLaunched;

	private final OneArgEvent<String> inputHandled;
	
	private final Thread inputHandlerThread;

	private final ErrorHandler errorHandler;

	public ConsoleGUIInputHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
		inputHandled = new OneArgEventImpl<>();
		isLaunched = false;
		inputHandlerThread = new Thread(this);
	}

	@Override
	public void run() {
		errorHandler.runInErrorHandler(this::inputHandlingMethod);
	}

	private void inputHandlingMethod() {
		Scanner scanner = new Scanner(System.in);
		while (isLaunched) {
			waitForInputInScanner();
			readInputFromScanner(scanner);
		}
	}

	private void waitForInputInScanner() {
		try {
			while (System.in.available() == 0) {
				if (!isLaunched) {
					throw new BasicAppError("Input handler has been terminated.");
				}
				waitSomeTime();
			}
		} catch (IOException exception) {
			throw new BasicAppError("IOException handlein in input handler.");
		}
	}

	private void readInputFromScanner(Scanner scanner) {
		String input = scanner.next();
		inputHandled.invoke(input);
	}

	private void waitSomeTime() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			throw new BasicAppError("Input handler forced to stop");
		}
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
}

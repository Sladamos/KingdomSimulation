package strategy.gui.console;

import strategy.app.AppInputHandler;
import strategy.error.BasicAppError;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleGUIInputHandler implements Runnable, AppInputHandler {

	private boolean isLaunched;

	private final OneArgEvent<String> inputHandled;
	
	private final Thread inputHandlerThread;

	public ConsoleGUIInputHandler() {
		inputHandled = new OneArgEventImpl<>();
		isLaunched = false;
		inputHandlerThread = new Thread(this);
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while (isLaunched) {
			waitForInputInScanner(scanner);
			readInputFromScanner(scanner);
		}
	}

	private void waitForInputInScanner(Scanner scanner) {
		while (!scanner.hasNext()) {
			if (!isLaunched) {
				throw new BasicAppError("Input handler has been terminated.");
			}
			waitSomeTime();
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

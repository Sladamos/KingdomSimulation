package strategy.gui.console;

import strategy.app.AppInputHandler;
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
			while (!scanner.hasNext()) {
				if (!isLaunched) {
					break;
				}
				Thread.sleep(200);
			}
			String input = scanner.next();
			inputHandled.invoke(input);

		}
	}

	@Override
	public synchronized void disableInputHandling() {
		isLaunched = false;
	}

	@Override
	public synchronized void enableInputHandling() {
		isLaunched = true;
		inputHandlerThread.start();
	}

	@Override
	public void addInputHandledListener(Consumer<String> listener) {
		inputHandled.addListener(listener);
	}
}

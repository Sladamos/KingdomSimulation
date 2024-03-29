package strategy.app.controller;

import strategy.app.inputhandller.AppInputHandler;
import strategy.buffer.SwitchableBuffer;
import strategy.option.executioner.OptionsExecutioner;
import strategy.util.ProtectedRunnableExecutorService;

import java.util.concurrent.ExecutorService;

public class AppControllerImpl implements AppController {

	private final AppInputHandler inputHandler;

	private final OptionsExecutioner optionsExecutioner;

	private final SwitchableBuffer<String> optionsBuffer;

	private ExecutorService executorService;

	private boolean isLaunched;

	public AppControllerImpl(AppInputHandler inputHandler, OptionsExecutioner optionsExecutioner, SwitchableBuffer<String> optionsBuffer) {
		this.inputHandler = inputHandler;
		this.optionsExecutioner = optionsExecutioner;
		this.optionsBuffer = optionsBuffer;
		isLaunched = false;
	}

	@Override
	public synchronized void waitForAppClose() {
        while(isLaunched) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
	}

	@Override
	public synchronized void enableExecutingOptions() {
		executorService = new ProtectedRunnableExecutorService();
        optionsBuffer.enableAcceptingItems();
		executorService.execute(inputHandler);
		executorService.execute(optionsExecutioner);
        isLaunched = true;
	}

	@Override
	public synchronized void disableExecutingOptions() {
		inputHandler.disableInputHandling();
        optionsExecutioner.disableExecuting();
        optionsBuffer.disableAcceptingItems();
		executorService.shutdown();
        isLaunched = false;
        notifyAll();
	}
}

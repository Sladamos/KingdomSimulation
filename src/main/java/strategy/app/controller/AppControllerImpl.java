package strategy.app.controller;

import strategy.app.inputhandller.AppInputHandler;
import strategy.buffer.SwitchableBuffer;
import strategy.option.OptionsExecutioner;

public class AppControllerImpl implements AppController {

	private final AppInputHandler inputHandler;

	private final OptionsExecutioner optionsExecutioner;

	private final SwitchableBuffer<String> optionsBuffer;

	private boolean isLaunched;

	public AppControllerImpl(AppInputHandler inputHandler, OptionsExecutioner optionsExecutioner, SwitchableBuffer<String> optionsBuffer) {
		this.inputHandler = inputHandler;
		this.optionsExecutioner = optionsExecutioner;
		this.optionsBuffer = optionsBuffer;
		isLaunched = false;
	}

	@Override
	public void waitForAppClose() {
        while(isLaunched) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
	}

	@Override
	public void enableExecutingOptions() {
        optionsBuffer.enableAcceptingItems();
        inputHandler.enableInputHandling();
        optionsExecutioner.enableExecuting();
        isLaunched = true;
	}

	@Override
	public void disableExecutingOptions() {
		inputHandler.disableInputHandling();
        optionsExecutioner.disableExecuting();
        optionsBuffer.disableAcceptingItems();
        isLaunched = false;
        notifyAll();
	}
}

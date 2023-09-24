package strategy.option;

import strategy.buffer.Buffer;
import strategy.buffer.BufferTerminatedException;
import strategy.error.BasicAppError;
import strategy.events.noargsevent.NoArgEvent;
import strategy.events.noargsevent.NoArgEventImpl;
import strategy.util.ProtectedThread;

import java.util.Map;

public class OptionsExecutionerImpl implements OptionsExecutioner, OptionsProvider {

	private final Buffer<String> optionsBuffer;

	private final Map<String, Option> options;

	private final NoArgEvent selectOption;

	private boolean isExecuting;

	public OptionsExecutionerImpl(Map<String, Option> options, Buffer<String> optionsBuffer) {
		this.options = options;
		this.optionsBuffer = optionsBuffer;
		isExecuting = false;
		selectOption = new NoArgEventImpl();
	}

	@Override
	public void disableExecuting() {
		isExecuting = false;
	}

	@Override
	public void run() {
		isExecuting = true;
		try {
			while (isExecuting) {
				selectOption.invoke();
				String selectedOptionName = optionsBuffer.getItem();
				Thread optionRunner = new ProtectedThread(() -> executeOptionWithName(selectedOptionName));
				optionRunner.start();
				waitForOptionEnd(optionRunner);
			}
		} catch (BufferTerminatedException exception) {
			disableExecuting();
		}
	}

	private void waitForOptionEnd(Thread optionRunner) {
		try {
			optionRunner.join();
		} catch (InterruptedException ignored) {
			isExecuting = false;
		}
	}

	private void executeOptionWithName(String selectedOptionName) {
		Option selectedOption = getOption(selectedOptionName);
		selectedOption.execute();
	}

	private synchronized Option getOption(String optionName) {
		if(!options.containsKey(optionName)) {
			throw new BasicAppError("Incorrect option name.");
		}
		return options.get(optionName);
	}

	@Override
	public void addSelectOptionListener(Runnable listener) {
		selectOption.addListener(listener);
	}
}

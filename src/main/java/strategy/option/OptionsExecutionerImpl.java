package strategy.option;

import strategy.buffer.Buffer;
import strategy.buffer.BufferTerminatedException;
import strategy.error.BasicAppError;
import strategy.util.ProtectedThread;

import java.util.Map;

public class OptionsExecutionerImpl implements OptionsExecutioner {

	private final Buffer<String> optionsBuffer;

	private final Map<String, Option> options;

	private boolean isExecuting;

	public OptionsExecutionerImpl(Map<String, Option> options, Buffer<String> optionsBuffer) {
		this.options = options;
		this.optionsBuffer = optionsBuffer;
		isExecuting = false;
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
}

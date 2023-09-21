package strategy.option;

import lombok.Getter;
import strategy.buffor.BufferImpl;
import strategy.buffor.SwitchableBuffer;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.util.ProtectedThread;

import java.util.HashMap;
import java.util.Map;

public class OptionsExecutionerImpl implements OptionsExecutioner {

	@Getter
	private final SwitchableBuffer<String> optionsBuffer;

	private final Map<String, Option> options;

	private final Thread optionsExecutionerThread;

	private boolean isExecuting;

	public OptionsExecutionerImpl() {
		optionsBuffer = new BufferImpl<>();
		isExecuting = false;
		options = new HashMap<>();
		optionsExecutionerThread = new ProtectedThread(this::run);
	}

	@Override
	public void enableExecuting() {
		isExecuting = true;
		if(!optionsExecutionerThread.isAlive()) {
			optionsExecutionerThread.start();
		}
	}

	@Override
	public void disableExecuting() {
		isExecuting = false;
	}

	@Override
	public synchronized void addOption(String optionName, Option option) {
		if(options.containsKey(optionName)) {
			throw new CriticalAppError("Incorrect option name.");
		}
		options.put(optionName, option);
	}

	private void run() {
		while (isExecuting) {
			String selectedOptionName = optionsBuffer.getItem();
			Thread optionRunner = new ProtectedThread(() -> executeOptionWithName(selectedOptionName));
			optionRunner.start();
			waitForOptionEnd(optionRunner);
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

package strategy.option;

import lombok.Getter;
import strategy.buffor.Buffor;
import strategy.buffor.BufforImpl;
import strategy.error.BasicAppError;
import strategy.util.ProtectedThread;

import java.util.HashMap;
import java.util.Map;

public class OptionsExecutionerImpl implements OptionsExecutioner {

	@Getter
	private final Buffor<String> optionsBuffor;

	private final Map<String, Option> options;

	private boolean isExecuting;

	public OptionsExecutionerImpl() {
		optionsBuffor = new BufforImpl<>();
		isExecuting = false;
		options = new HashMap<>();
	}

	@Override
	public void run() {
		isExecuting = true;
		while (isExecuting) {
			String selectedOptionName = optionsBuffor.getItem();
			Thread optionRunner = new ProtectedThread(() -> executeOptionWithName(selectedOptionName));
			optionRunner.start();
			try {
				optionRunner.join();
			} catch (InterruptedException ignored) {
				break;
			}
		}
	}

	private void executeOptionWithName(String selectedOptionName) {
		Option selectedOption = getOption(selectedOptionName);
		selectedOption.execute();
	}

	private Option getOption(String optionName) {
		if(!options.containsKey(optionName)) {
			throw new BasicAppError("Incorrect option name.");
		}
		return options.get(optionName);
	}

	@Override
	public void disableExecuting() {
		isExecuting = false;
	}
}

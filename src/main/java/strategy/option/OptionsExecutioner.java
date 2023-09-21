package strategy.option;

import strategy.buffor.SwitchableBuffer;

public interface OptionsExecutioner {
	SwitchableBuffer<String> getOptionsBuffer();
	void disableExecuting();
	void addOption(String optionName, Option option);
	void enableExecuting();
}

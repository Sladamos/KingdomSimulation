package strategy.option;

import strategy.buffor.Buffor;

public interface OptionsExecutioner extends Runnable {
	Buffor<String> getOptionsBuffor();
	void disableExecuting();
	void addOption(String optionName, Option option);
}

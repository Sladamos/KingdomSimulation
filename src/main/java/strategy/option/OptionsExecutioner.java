package strategy.option;

import strategy.buffor.Buffor;

public interface OptionsExecutioner extends Runnable {
	Buffor<String> getOptionsBuffor();
	void disableExecuting();
}

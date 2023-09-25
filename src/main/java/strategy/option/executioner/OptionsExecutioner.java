package strategy.option.executioner;

public interface OptionsExecutioner extends Runnable, OptionsProvider {
	void disableExecuting();
}

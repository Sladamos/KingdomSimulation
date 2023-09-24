package strategy.option;

public interface OptionsExecutioner extends Runnable, OptionsProvider {
	void disableExecuting();
}

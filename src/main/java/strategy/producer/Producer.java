package strategy.producer;

public interface Producer extends Runnable {
	boolean isWorking();
	void terminate();
}

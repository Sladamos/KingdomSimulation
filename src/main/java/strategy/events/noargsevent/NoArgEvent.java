package strategy.events.noargsevent;

public interface NoArgEvent {
	void addListener(Runnable listener);
	void removeListener(Runnable listener);
	void invoke();
	void removeAllListeners();
}

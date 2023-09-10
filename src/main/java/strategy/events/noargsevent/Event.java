package strategy.events.noargsevent;

public interface Event {
	void addListener(Runnable listener);
	void removeListener(Runnable listener);
	void invoke();
}

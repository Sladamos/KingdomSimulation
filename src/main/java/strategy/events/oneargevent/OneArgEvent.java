package strategy.events.oneargevent;

import java.util.function.Consumer;

public interface OneArgEvent<T> {
	void addListener(Consumer<T> listener);
	void removeListener(Consumer<T> listener);
	void invoke(T arg);
	void removeAllListeners();
}

package strategy.message;

import java.util.function.Consumer;

public interface MessagesNotifier<T extends Message<?>> extends Consumer<T> {
	void addListener(Consumer<T> listener);
}

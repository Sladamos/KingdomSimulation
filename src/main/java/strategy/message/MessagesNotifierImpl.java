package strategy.message;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.util.function.Consumer;

public class MessagesNotifierImpl<T extends Message<?>> implements MessagesNotifier<T> {
	
	private final OneArgEvent<T> notifyEvent;

	public MessagesNotifierImpl() {
		notifyEvent = new OneArgEventImpl<>();
	}

	@Override
	public void addListener(Consumer<T> listener) {
		notifyEvent.addListener(listener);
	}

	@Override
	public void accept(T t) {
		notifyEvent.invoke(t);
	}
}

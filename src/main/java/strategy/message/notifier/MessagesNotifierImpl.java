package strategy.message.notifier;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.message.Message;
import strategy.message.receiver.MessagesReceiver;

public class MessagesNotifierImpl<T extends Message<?>> implements MessagesNotifier<T> {
	
	private final OneArgEvent<T> notifyEvent;

	public MessagesNotifierImpl() {
		notifyEvent = new OneArgEventImpl<>();
	}

	@Override
	public void addListener(MessagesReceiver<T> listener) {
		notifyEvent.addListener(listener);
	}

	@Override
	public void accept(T t) {
		notifyEvent.invoke(t);
	}

	@Override
	public void removeListeners() {
		notifyEvent.removeAllListeners();
	}
}

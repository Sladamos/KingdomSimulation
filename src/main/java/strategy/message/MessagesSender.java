package strategy.message;

import strategy.message.receiver.MessagesReceiver;

public interface MessagesSender<T extends Message<?>> {
	void addListener(MessagesReceiver<T> messagesReceiver);
}

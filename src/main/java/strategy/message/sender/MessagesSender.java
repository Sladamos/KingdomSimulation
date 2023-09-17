package strategy.message.sender;

import strategy.message.Message;
import strategy.message.receiver.MessagesReceiver;

public interface MessagesSender<T extends Message<?>> {
	void addListener(MessagesReceiver<T> messagesReceiver);
}

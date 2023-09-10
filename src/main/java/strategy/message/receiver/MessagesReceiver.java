package strategy.message.receiver;

import strategy.message.Message;

public interface MessagesReceiver<T extends Message<?>> {
	void receiveMessage(T message);
}

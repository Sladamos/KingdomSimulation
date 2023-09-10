package strategy.message.receiver;

import strategy.message.Message;

public class ConsoleMessagesReceiver<T extends Message<?>> implements MessagesReceiver<T> {
	@Override
	public void receiveMessage(T message) {
		System.out.println(message);
	}
}

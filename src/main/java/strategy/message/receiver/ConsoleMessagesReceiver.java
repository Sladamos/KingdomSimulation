package strategy.message.receiver;

import strategy.message.Message;

public class ConsoleMessagesReceiver implements MessagesReceiver {
	@Override
	public void receiveMessage(Message message) {
		System.out.println(message);
	}
}

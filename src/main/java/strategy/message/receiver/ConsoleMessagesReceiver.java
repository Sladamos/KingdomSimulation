package strategy.message.receiver;

import strategy.message.StringMessage;

public class ConsoleMessagesReceiver implements MessagesReceiver {
	@Override
	public void receiveMessage(StringMessage message) {
		System.out.println(message);
	}
}

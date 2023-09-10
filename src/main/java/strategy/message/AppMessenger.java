package strategy.message;

import strategy.message.receiver.MessagesReceiver;

public class AppMessenger {
	private MessagesReceiver<StringMessage> errorMessagesReceiver;
	private MessagesReceiver<StringMessage> battleMessagesReceiver;
}

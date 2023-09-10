package strategy.message.receiver;

import strategy.message.StringMessage;

public interface MessagesReceiver {
	void receiveMessage(StringMessage message);
}

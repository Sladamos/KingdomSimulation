package strategy.message.receiver;

import strategy.message.Message;

public interface MessagesReceiver {
	void receiveMessage(Message message);
}

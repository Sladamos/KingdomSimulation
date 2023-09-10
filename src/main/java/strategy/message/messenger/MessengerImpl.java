package strategy.message.messenger;

import strategy.message.JSONMessage;
import strategy.message.StringMessage;
import strategy.message.receiver.MessagesReceiver;

public class MessengerImpl implements Messenger {
	private MessagesReceiver<StringMessage> stringMessageMessagesReceiver;
	private MessagesReceiver<JSONMessage> jsonMessageMessagesReceiver;

	@Override
	public void receiveMessage(JSONMessage message) {
		jsonMessageMessagesReceiver.receiveMessage(message);
	}

	@Override
	public void receiveMessage(StringMessage message) {
		stringMessageMessagesReceiver.receiveMessage(message);
	}
}

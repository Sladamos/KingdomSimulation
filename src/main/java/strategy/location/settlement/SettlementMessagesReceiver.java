package strategy.location.settlement;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.message.JSONMessage;
import strategy.message.MessagesNotifier;
import strategy.message.receiver.MessagesReceiver;

public class SettlementMessagesReceiver implements MessagesReceiver<JSONMessage> {

	private final OneArgEvent<JSONMessage> messageEvent;

	public SettlementMessagesReceiver(MessagesNotifier<JSONMessage> messagesNotifier) {
		messageEvent = new OneArgEventImpl<>();
		messagesNotifier.addListener(this);
	}

	@Override
	public void accept(JSONMessage message) {
		JSONMessage newMessage = new JSONMessage(message);
		newMessage.put("sender", "settlement");
		messageEvent.invoke(newMessage);
	}
}

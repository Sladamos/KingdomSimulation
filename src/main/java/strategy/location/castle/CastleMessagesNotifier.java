package strategy.location.castle;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.location.LocationMessagesNotifier;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.message.receiver.MessagesReceiver;

public class CastleMessagesNotifier implements LocationMessagesNotifier {

	private final OneArgEvent<JSONMessage> messageEvent;

	public CastleMessagesNotifier(MessagesNotifier<JSONMessage> messagesNotifier) {
		messageEvent = new OneArgEventImpl<>();
		messagesNotifier.addListener(this);
	}

	@Override
	public void accept(JSONMessage message) {
		JSONMessage newMessage = new JSONMessage(message);
		newMessage.put("sender", "castle");
		messageEvent.invoke(newMessage);
	}

	@Override
	public void addListener(MessagesReceiver<JSONMessage> listener) {
		messageEvent.addListener(listener);
	}
}

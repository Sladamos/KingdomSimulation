package strategy.location.mountain;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.location.LocationMessagesNotifier;
import strategy.message.JSONMessage;
import strategy.message.MessagesNotifier;
import strategy.message.receiver.MessagesReceiver;

public class MountainMessagesNotifier implements LocationMessagesNotifier {

	private final OneArgEvent<JSONMessage> messageEvent;

	public MountainMessagesNotifier(MessagesNotifier<JSONMessage> messagesNotifier) {
		messageEvent = new OneArgEventImpl<>();
		messagesNotifier.addListener(this);
	}

	@Override
	public void accept(JSONMessage message) {
		JSONMessage newMessage = new JSONMessage(message);
		newMessage.put("sender", "mountain");
		messageEvent.invoke(newMessage);
	}

	@Override
	public void addListener(MessagesReceiver<JSONMessage> listener) {
		messageEvent.addListener(listener);
	}
}

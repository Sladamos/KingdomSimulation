package strategy.kingdom.notifier;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.location.LocationMessagesNotifier;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;

public class KingdomMessagesNotifierImpl implements KingdomMessagesNotifier {

	private final OneArgEvent<JSONMessage> messageEvent;

	public KingdomMessagesNotifierImpl() {
		messageEvent = new OneArgEventImpl<>();
	}

	@Override
	public void bindLocationNotifier(LocationMessagesNotifier messagesNotifier) {
		messagesNotifier.addListener(this);
	}

	@Override
	public void addListener(MessagesReceiver<JSONMessage> listener) {
		messageEvent.addListener(listener);
	}

	@Override
	public void accept(JSONMessage message) {
		JSONMessage newMessage = new JSONMessage(message);
		newMessage.put("sender", "mountain");
		messageEvent.invoke(newMessage);
	}

	@Override
	public void removeListeners() {
		messageEvent.removeAllListeners();
	}
}

package strategy.kingdom.notifier;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.location.LocationMessagesNotifier;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;

public class KingdomMessagesNotifierImpl implements KingdomMessagesNotifier {

	private final OneArgEvent<JSONMessage> messageEvent;

	private final String kingdomId;

	public KingdomMessagesNotifierImpl(String kingdomId) {
		messageEvent = new OneArgEventImpl<>();
		this.kingdomId = kingdomId;
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
		try {
			String strMessage = kingdomId + ": " + message.getContent().getString("message");
			newMessage.put("message", strMessage);
		} catch (Exception ignored) {}
		newMessage.put("owner", kingdomId);
		messageEvent.invoke(newMessage);
	}

	@Override
	public void removeListeners() {
		messageEvent.removeAllListeners();
	}
}

package strategy.location.settlement;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.location.LocationMessagesNotifier;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;

public class SettlementMessagesNotifier implements LocationMessagesNotifier {

	private final OneArgEvent<JSONMessage> messageEvent;

	public SettlementMessagesNotifier() {
		messageEvent = new OneArgEventImpl<>();
	}

	@Override
	public void accept(JSONMessage message) {
		JSONMessage newMessage = new JSONMessage(message);
		newMessage.put("sender", "settlement");
		messageEvent.invoke(newMessage);
	}

	@Override
	public void addListener(MessagesReceiver<JSONMessage> listener) {
		messageEvent.addListener(listener);
	}

	@Override
	public void removeListeners() {
		messageEvent.removeAllListeners();
	}
}

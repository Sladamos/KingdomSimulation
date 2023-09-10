package strategy.kingdom;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.message.JSONMessage;
import strategy.message.MessagesNotifier;
import strategy.message.MessagesSender;
import strategy.message.receiver.MessagesReceiver;

public class KingdomNotifier implements MessagesNotifier<JSONMessage> {

	private final OneArgEvent<JSONMessage> messageEvent;

	public KingdomNotifier() {
		messageEvent = new OneArgEventImpl<>();
	}

	public void bindSender(MessagesSender<JSONMessage> messagesSender) {
		messagesSender.addListener(this);
	}

	@Override
	public void addListener(MessagesReceiver<JSONMessage> listener) {
		messageEvent.addListener(listener);
	}

	@Override
	public void accept(JSONMessage message) {
		JSONMessage newMessage = new JSONMessage(message);
		newMessage.put("sender", "mountain");
		messageEvent.invoke(message);
	}
}

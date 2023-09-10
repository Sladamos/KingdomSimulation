package strategy.kingdom;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.message.JSONMessage;
import strategy.message.MessagesNotifier;
import strategy.message.receiver.MessagesReceiver;

public class KingdomNotifier implements MessagesNotifier<JSONMessage> {

	private final MessagesNotifier<JSONMessage> villageNotifier;

	private final MessagesNotifier<JSONMessage> castleNotifier;

	private final MessagesNotifier<JSONMessage> mountainNotifier;

	private final MessagesNotifier<JSONMessage> settlementNotifier;

	private final OneArgEvent<JSONMessage> messageEvent;

	public KingdomNotifier() {x
		messageEvent = new OneArgEventImpl<>();
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

package strategy.kingdom.notifier;

import strategy.location.LocationMessagesNotifier;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;

public interface KingdomMessagesNotifier extends MessagesNotifier<JSONMessage> {
	void bindLocationNotifier(LocationMessagesNotifier messagesNotifier);
}

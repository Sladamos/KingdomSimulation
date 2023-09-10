package strategy.kingdom;

import strategy.location.LocationMessagesNotifier;
import strategy.message.JSONMessage;
import strategy.message.MessagesNotifier;

public interface KingdomMessagesNotifier extends MessagesNotifier<JSONMessage> {
	void bindLocationNotifier(LocationMessagesNotifier messagesNotifier);
}

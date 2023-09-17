package strategy.location.village;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.location.LocationMessagesNotifier;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;

public class VillageMessagesNotifier implements LocationMessagesNotifier {

    private final OneArgEvent<JSONMessage> messageEvent;

    public VillageMessagesNotifier() {
        messageEvent = new OneArgEventImpl<>();
    }

    @Override
    public void accept(JSONMessage message) {
        JSONMessage newMessage = new JSONMessage(message);
        newMessage.put("sender", "village");
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

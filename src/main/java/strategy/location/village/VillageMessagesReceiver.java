package strategy.location.village;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.message.JSONMessage;
import strategy.message.MessagesNotifier;
import strategy.message.receiver.MessagesReceiver;

public class VillageMessagesReceiver implements MessagesNotifier<JSONMessage> {

    private final OneArgEvent<JSONMessage> messageEvent;

    public VillageMessagesReceiver(MessagesNotifier<JSONMessage> messagesNotifier) {
        messageEvent = new OneArgEventImpl<>();
        messagesNotifier.addListener(this);
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
}

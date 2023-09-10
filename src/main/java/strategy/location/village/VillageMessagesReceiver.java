package strategy.location.village;

import strategy.events.oneargevent.OneArgEvent;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;

public class VillageMessagesReceiver implements MessagesReceiver<JSONMessage> {

    private OneArgEvent<JSONMessage> messageEvent;

    @Override
    public void receiveMessage(JSONMessage message) {
        JSONMessage newMessage = new JSONMessage(message);
        newMessage.put("sender", "village");
        messageEvent.invoke(newMessage);
    }
}

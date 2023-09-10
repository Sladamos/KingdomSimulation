package strategy.message.messenger;

import strategy.message.JSONMessage;
import strategy.message.StringMessage;

public interface Messenger {
    void receiveMessage(JSONMessage message);
    void receiveMessage(StringMessage message);
}

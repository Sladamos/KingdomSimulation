package strategy.message.logging;

import strategy.message.JSONMessage;
import strategy.message.StringMessage;

public interface Logger {
    void logMessage(JSONMessage message);
    void logMessage(StringMessage message);
}

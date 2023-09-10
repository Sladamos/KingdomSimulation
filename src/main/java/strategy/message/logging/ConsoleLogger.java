package strategy.message.logging;

import strategy.message.JSONMessage;
import strategy.message.StringMessage;

public class ConsoleLogger implements Logger {
    @Override
    public void logMessage(JSONMessage message) {
        System.out.println(message);
    }

    @Override
    public void logMessage(StringMessage message) {
        System.out.println(message);
    }
}

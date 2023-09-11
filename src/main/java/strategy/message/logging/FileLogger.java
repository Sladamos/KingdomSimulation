package strategy.message.logging;

import org.slf4j.LoggerFactory;
import strategy.message.JSONMessage;
import strategy.message.StringMessage;

public class FileLogger implements Logger {
    private final org.slf4j.Logger logger;

    public FileLogger() {
         logger = LoggerFactory.getLogger("FILE");
    }

    @Override
    public void logMessage(JSONMessage message) {
        logger.debug(message.toString());
    }

    @Override
    public void logMessage(StringMessage message) {
        logger.debug(message.toString());
    }
}

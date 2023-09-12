package strategy.error;

import strategy.message.JSONMessage;
import strategy.message.StringMessage;
import strategy.message.receiver.MessagesReceiver;
import strategy.message.sender.MessagesSender;

public interface ErrorHandler extends MessagesSender<JSONMessage> {
    void runInErrorHandler(Runnable runnable);
}

package strategy.message.notifier;

import strategy.message.Message;
import strategy.message.sender.MessagesSender;
import strategy.message.receiver.MessagesReceiver;

public interface MessagesNotifier<T extends Message<?>> extends MessagesReceiver<T>, MessagesSender<T> {
}

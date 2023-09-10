package strategy.message;

import strategy.message.receiver.MessagesReceiver;

public interface MessagesNotifier<T extends Message<?>> extends MessagesReceiver<T>, MessagesSender<T> {
}

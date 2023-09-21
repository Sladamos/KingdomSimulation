package strategy;

import strategy.buffer.Switchable;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;

public interface StorageManager extends Switchable {
    MessagesNotifier<JSONMessage> getStorageMessagesNotifier();
}

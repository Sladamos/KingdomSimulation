package strategy;

import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;

public interface StorageManager {
    void enableAcceptingItems();
    void disableAcceptingItems();
    MessagesNotifier<JSONMessage> getStorageMessagesNotifier();
}

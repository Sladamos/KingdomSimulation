package strategy;

import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.storage.SwitchableStorage;

public interface StorageManager extends SwitchableStorage {
    MessagesNotifier<JSONMessage> getStorageMessagesNotifier();
}

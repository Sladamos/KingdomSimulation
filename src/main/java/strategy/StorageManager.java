package strategy;

import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.buffor.SwitchableBuffor;

public interface StorageManager extends SwitchableBuffor {
    MessagesNotifier<JSONMessage> getStorageMessagesNotifier();
}

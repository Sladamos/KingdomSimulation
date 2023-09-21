package strategy;

import strategy.buffor.Switchable;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.buffor.SwitchableBuffer;

public interface StorageManager extends Switchable {
    MessagesNotifier<JSONMessage> getStorageMessagesNotifier();
}

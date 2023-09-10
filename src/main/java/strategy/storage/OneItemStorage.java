package strategy.storage;

import strategy.StorageManager;
import strategy.item.Item;
import strategy.message.JSONMessage;
import strategy.message.Message;

import java.util.function.Consumer;

public interface OneItemStorage<T extends Item> extends StorageManager {
    void addListener(Consumer<JSONMessage> messageConsumer);
    void addItemToStorage(T item);
    T getItemFromStorage();
}

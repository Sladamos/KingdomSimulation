package strategy.storage;

import strategy.item.Item;
import strategy.message.JSONMessage;
import strategy.message.sender.MessagesSender;

public interface OneItemStorage<T extends Item> extends MessagesSender<JSONMessage>, SwitchableStorage {
    void addItemToStorage(T item);
    T getItemFromStorage();
}

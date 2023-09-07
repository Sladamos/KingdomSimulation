package strategy.storage;

import strategy.StorageManager;
import strategy.item.Item;

public interface OneItemStorage<T extends Item> extends StorageManager {
    void addItemToStorage(T item);
    T getItemFromStorage();
}

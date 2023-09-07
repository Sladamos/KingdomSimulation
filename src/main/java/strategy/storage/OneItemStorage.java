package strategy.storage;

import strategy.item.Item;

public interface OneItemStorage<T extends Item>{
    void addItemToStorage(T item);
    T getItemFromStorage();
    void stopServingItems();
}

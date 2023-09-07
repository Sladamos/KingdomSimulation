package strategy.storage;

import strategy.item.Item;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class UnlimitedOneItemStorage<T extends Item> implements OneItemStorage<T> {

    private final Deque<T> storage;

    private boolean working;

    public UnlimitedOneItemStorage() {
        storage = new ArrayDeque<>();
    }

    public UnlimitedOneItemStorage(Collection<? extends T> storageInitializer) {
        storage = new ArrayDeque<>(storageInitializer);
    }

    @Override
    public synchronized void addItemToStorage(T item) {
        storage.push(item);
        notifyAll();
    }

    @Override
    public synchronized T getItemFromStorage() {
        waitForItemInStorage();
        if (!isWorking()) {
            throw new StorageTerminatedException();
        }
        return storage.pop();
    }

    @Override
    public synchronized void stopServingItems() {
        working = false;
        notifyAll();
    }

    private synchronized boolean isWorking() {
        return working;
    }

    private synchronized void waitForItemInStorage() {
        while(storage.size() == 0 && isWorking()) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
    }
}

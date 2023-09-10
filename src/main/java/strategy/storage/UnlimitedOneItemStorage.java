package strategy.storage;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.item.Item;
import strategy.message.JSONMessage;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class UnlimitedOneItemStorage<T extends Item> implements OneItemStorage<T> {

    private final Deque<T> storage;

    private boolean working;

    private final OneArgEvent<JSONMessage> messageEvent;

    public UnlimitedOneItemStorage() {
        storage = new ArrayDeque<>();
        messageEvent = new OneArgEventImpl<>();
    }

    public UnlimitedOneItemStorage(Collection<? extends T> storageInitializer) {
        storage = new ArrayDeque<>(storageInitializer);
        messageEvent = new OneArgEventImpl<>();
    }

    @Override
    public synchronized void addItemToStorage(T item) {
        storage.push(item);
        String strMessage = "Item pushed to storage: " + item;
        sendMessage(strMessage, item);
        notifyAll();
    }

    @Override
    public synchronized T getItemFromStorage() {
        waitForItemInStorage();
        if (!isWorking()) {
            throw new StorageTerminatedException();
        }
        T item = storage.pop();
        String strMessage = "Item popped from storage: " + item;
        sendMessage(strMessage, item);
        return item;
    }

    @Override
    public synchronized void enableAcceptingItems() {
        working = true;
    }

    @Override
    public synchronized void disableAcceptingItems() {
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

    private void sendMessage(String strMessage, T item) {
        JSONMessage message = new JSONMessage(strMessage);
        message.put("size", storage.size());
        message.put("item", item.toString());
        System.out.println(strMessage);
        messageEvent.invoke(message);
    }
}

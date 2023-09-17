package strategy.storage;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.item.Item;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class UnlimitedOneItemStorage<T extends Item> implements OneItemStorage<T> {

    private final Deque<T> storage;

    private boolean isWorking;

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
    public void addListener(MessagesReceiver<JSONMessage> messageConsumer) {
        messageEvent.addListener(messageConsumer);
    }

    @Override
    public synchronized void addItemToStorage(T item) {
        if(isWorking) {
            storage.push(item);
            String strMessage = "Item pushed to storage: " + item;
            sendMessage(strMessage, item);
            notifyAll();
        }
    }

    @Override
    public synchronized T getItemFromStorage() {
        waitForItemInStorage();
        if (isWorking) {
            throw new StorageTerminatedException();
        }
        T item = storage.pop();
        sendMessage(item);
        return item;
    }

    @Override
    public synchronized void enableAcceptingItems() {
        isWorking = true;
    }

    @Override
    public synchronized void disableAcceptingItems() {
        isWorking = false;
        notifyAll();
    }

    private void waitForItemInStorage() {
        while(storage.isEmpty() && isWorking) {
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
        messageEvent.invoke(message);
    }

    private void sendMessage(T item) {
        JSONMessage message = new JSONMessage();
        message.put("size", storage.size());
        message.put("item", item.toString());
        messageEvent.invoke(message);
    }
}

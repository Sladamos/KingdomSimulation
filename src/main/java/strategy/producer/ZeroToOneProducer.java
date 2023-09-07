package strategy.producer;

import strategy.item.Item;
import strategy.storage.OneItemStorage;

public abstract class ZeroToOneProducer<T extends Item> implements OneItemProducer<T> {

	private final OneItemStorage<T> destinationStorage;

	private final int numberOfItemsPerMinute;

	private boolean isWorking;

	public ZeroToOneProducer(OneItemStorage<T> destinationStorage, ProducerConfig producerConfig) {
		this.destinationStorage = destinationStorage;
		this.numberOfItemsPerMinute = 1;//TODO: producerConfig.getNumberOfItemsPerMinute();
	}

	@Override
	public void run() {
		isWorking = true;
		while(isWorking()) {
			try {
				T item = produceNewItem();
				destinationStorage.addItemToStorage(item);
			} catch (Exception err) {
				return;
			}
		}
	}
	@Override
	public synchronized void terminate() {
		isWorking = false;
	}

	protected abstract T createNewItem();

	private T produceNewItem() throws InterruptedException {
		Thread.sleep(60000L / numberOfItemsPerMinute);
		return createNewItem();
	}

	private synchronized boolean isWorking() {
		return isWorking;
	}
}


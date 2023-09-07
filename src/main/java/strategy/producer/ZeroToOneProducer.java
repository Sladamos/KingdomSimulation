package strategy.producer;

import strategy.item.Item;
import strategy.storage.OneItemStorage;

public abstract class ZeroToOneProducer<T extends Item> implements Producer {

	private final OneItemStorage<T> destinationStorage;

	private final int numberOfItemsPerMinute;

	private boolean isWorking;

	public ZeroToOneProducer(OneItemStorage<T> destinationStorage, ProducerConfig producerConfig) {
		this.destinationStorage = destinationStorage;
		this.numberOfItemsPerMinute = producerConfig.getNumberOfItemsPerMinute();
	}

	@Override
	public void run() {
		isWorking = true;
		while(isWorking()) {
			try {
				T item = produceNewItem();
				////TODO: messages receiver System.out.println("Produced :" + item);
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


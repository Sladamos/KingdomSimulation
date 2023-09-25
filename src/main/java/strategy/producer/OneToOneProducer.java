package strategy.producer;

import strategy.item.Item;
import strategy.storage.OneItemStorage;

public abstract class OneToOneProducer<T extends Item, U extends Item> implements OneItemProducer<U> {

	private final OneItemStorage<U> destinationStorage;

	private final OneItemStorage<T> sourceStorage;

	private final int numberOfItemsPerMinute;

	private boolean isWorking;

	public OneToOneProducer(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
		this.sourceStorage = sourceStorage;
		this.destinationStorage = destinationStorage;
		this.numberOfItemsPerMinute = producerConfig.getNumberOfItemsPerMinute();
	}

	@Override
	public void run() {
		isWorking = true;
		while(isWorking()) {
			try {
				T material = sourceStorage.getItem();
				U item = produceNewItem(material);
				destinationStorage.addItem(item);
			} catch (Exception err) {
				return;
			}
		}
	}
	@Override
	public synchronized void terminate() {
		isWorking = false;
	}

	protected abstract U createNewItem(T material);

	private U produceNewItem(T material) throws InterruptedException {
		Thread.sleep(60000L / numberOfItemsPerMinute);
		return createNewItem(material);
	}

	private synchronized boolean isWorking() {
		return isWorking;
	}
}


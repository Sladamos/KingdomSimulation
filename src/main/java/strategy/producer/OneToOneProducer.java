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
				T material = sourceStorage.getItemFromStorage();
				//TODO: messages receiver System.out.println("Consumed :" + material);
				U item = produceNewItem(material);
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

	protected abstract U createNewItem(T material);

	private U produceNewItem(T material) throws InterruptedException {
		Thread.sleep(60000L / numberOfItemsPerMinute);
		return createNewItem(material);
	}

	private synchronized boolean isWorking() {
		return isWorking;
	}
}


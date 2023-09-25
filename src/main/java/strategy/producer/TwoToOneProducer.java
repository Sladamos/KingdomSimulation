package strategy.producer;

import strategy.item.Item;
import strategy.storage.OneItemStorage;

public abstract class TwoToOneProducer<T extends Item, U extends Item, V extends Item> implements OneItemProducer<V> {

	private final OneItemStorage<V> destinationStorage;

	private final OneItemStorage<T> firstSourceStorage;

	private final OneItemStorage<U> secondSourceStorage;

	private final int numberOfItemsPerMinute;

	private boolean isWorking;

	public TwoToOneProducer(OneItemStorage<T> firstSourceStorage, OneItemStorage<U> secondSourceStorage, OneItemStorage<V> destinationStorage, ProducerConfig producerConfig) {
		this.firstSourceStorage = firstSourceStorage;
		this.secondSourceStorage = secondSourceStorage;
		this.destinationStorage = destinationStorage;
		this.numberOfItemsPerMinute = producerConfig.getNumberOfItemsPerMinute();
	}

	@Override
	public void run() {
		isWorking = true;
		while(isWorking()) {
			try {
				T firstMaterial = firstSourceStorage.getItem();
				U secondMaterial = secondSourceStorage.getItem();
				V item = produceNewItem(firstMaterial, secondMaterial);
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

	protected abstract V createNewItem(T firstMaterial, U secondMaterial);

	private V produceNewItem(T firstMaterial, U secondMaterial) throws InterruptedException {
		Thread.sleep(60000L / numberOfItemsPerMinute);
		return createNewItem(firstMaterial, secondMaterial);
	}

	private synchronized boolean isWorking() {
		return isWorking;
	}
}


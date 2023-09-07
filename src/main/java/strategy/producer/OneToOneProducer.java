package strategy.producer;

import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import strategy.item.Item;
import strategy.producer.exceptions.ProducerDestroyedException;
import strategy.producer.exceptions.IncorrectDamageException;
import strategy.producer.exceptions.IncorrectStorageException;
import strategy.producer.exceptions.ProducerTerminatedException;
import strategy.storage.OneItemStorage;

import java.util.ArrayDeque;
import java.util.function.Supplier;

public abstract class OneToOneProducer<T extends Item, U extends Item> implements OneItemProducer<U> {

	private final OneItemStorage<U> destinationStorage;

	private final OneItemStorage<T> sourceStorage;

	private final double producingSpeed;

	private boolean isWorking;

	public OneToOneProducer(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
		this.sourceStorage = sourceStorage;
		this.destinationStorage = destinationStorage;
		this.durability = durability;
		this.producingSpeed = producingSpeed;
		checkInitParameters(defaultStorageSize);
		initiallyFillStorageWithItems(defaultStorageSize);
		isWorking = true;
	}

	@Override
	public void run() {
		while(!isDestroyed() && isWorking()) {
			try {
				T material = sourceStorage.get();
				System.out.println("Consumed :" + material);
				Thread.sleep((long) (getProducingTime() / producingSpeed));
				if(!isDestroyed() && isWorking()) {
					U item = produceNewItem(material);
					System.out.println("Produced :" + item);
					store(item);
				}
			} catch (Exception err) {
				return;
			}
		}
	}

	@Override
	public synchronized void dealDamage(int damage) {
		if(damage < 0) {
			throw new IncorrectDamageException("Damage must be a non negative number");
		}

		if(isDestroyed()) {
			throw new ProducerDestroyedException("It's not possible to attack destroyed building");
		}

		durability -= damage;
		durability = Math.max(0, durability);

		if(durability == 0) {
			isDestroyed = true;
			notifyAll();
		}
	}

	@Override
	public synchronized boolean isDestroyed() {
		return isDestroyed;
	}

	@Override
	public synchronized void terminate() {
		isWorking = false;
		notifyAll();
	}

	@Override
	public synchronized U getItem() {
		waitForItemInStorage();
		if(isDestroyed()) {
			throw new ProducerDestroyedException();
		}
		if(!isWorking()) {
			throw new ProducerTerminatedException();
		}
		return storage.pop();
	}

	public synchronized boolean isWorking() {
		return isWorking;
	}

	protected abstract U produceNewItem(T material);

	protected abstract U produceNewItem();

	protected abstract int getProducingTime();

	private void checkInitParameters(int storageSize) {
		if (storageSize < 0) {
			throw new IncorrectStorageException("Storage size must be a non negative number");
		}
	}

	private void initiallyFillStorageWithItems(int numberOfItems) {
		for(int i = 0; i < numberOfItems; i++) {
			U item = produceNewItem();
			store(item);
		}
	}
}


package strategy.producer;

import lombok.Getter;
import lombok.Synchronized;
import strategy.producer.exceptions.ProducerDestroyedException;
import strategy.producer.exceptions.IncorrectDamageException;
import strategy.producer.exceptions.IncorrectStorageException;
import strategy.producer.exceptions.ProducerTerminatedException;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class ZeroToOneProducer<T> implements OneItemProducer<T> {

	private final Deque<T> storage;

	private final double producingSpeed;

	@Getter(onMethod_ = {@Synchronized})
	private int durability;

	private boolean isDestroyed;

	private boolean isWorking;

	public ZeroToOneProducer(int defaultStorageSize, double producingSpeed, int durability) {
		isDestroyed = false;
		this.durability = durability;
		this.producingSpeed = producingSpeed;
		storage = new ArrayDeque<>();
		checkInitParameters(defaultStorageSize);
		initiallyFillStorageWithItems(defaultStorageSize);
		isWorking = true;
	}

	@Override
	public void run() {
		while (!isDestroyed() && isWorking()) {
			try {
				Thread.sleep((long) (getProducingTime() / producingSpeed));
				if (!isDestroyed() && isWorking()) {
					T item = produceNewItem();
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
		if (damage < 0) {
			throw new IncorrectDamageException("Damage must be a non negative number");
		}

		if (isDestroyed()) {
			throw new ProducerDestroyedException("It's not possible to attack destroyed building");
		}

		durability -= damage;
		durability = Math.max(0, durability);

		if (durability == 0) {
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
	public synchronized T getItem() {
		waitForItemInStorage();
		if (isDestroyed()) {
			throw new ProducerDestroyedException();
		}
		if (!isWorking()) {
			throw new ProducerTerminatedException();
		}
		return storage.pop();
	}

	public synchronized boolean isWorking() {
		return isWorking;
	}

	public synchronized void store(T item) {
		storage.push(item);
		notifyAll();
	}

	public synchronized int getNumberOfItemsInStorage() {
		return storage.size();
	}

	protected abstract T produceNewItem();

	protected abstract int getProducingTime();

	private void checkInitParameters(int storageSize) {
		if (storageSize < 0) {
			throw new IncorrectStorageException("Storage size must be a non negative number");
		}
	}

	private void initiallyFillStorageWithItems(int numberOfItems) {
		for (int i = 0; i < numberOfItems; i++) {
			T item = produceNewItem();
			store(item);
		}
	}

	private synchronized void waitForItemInStorage() {
		while (storage.size() == 0 && !isDestroyed() && isWorking()) {
			try {
				wait();
			} catch (InterruptedException ignored) {
			}
		}
	}
}



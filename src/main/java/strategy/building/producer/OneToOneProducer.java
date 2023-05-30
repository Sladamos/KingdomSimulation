package strategy.building.producer;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class OneToOneProducer<T, U> implements OneItemProducer<U> {

	private final Deque<U> storage;

	private final double producingSpeed;

	private final Supplier<T> producer;

	@Getter(onMethod_={@Synchronized})
	private int durability;

	private boolean isDestroyed;

	public OneToOneProducer(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		this.producer = producer;
		isDestroyed = false;
		this.durability = durability;
		this.producingSpeed = producingSpeed;
		storage = new ArrayDeque<>();
		checkInitParameters(defaultStorageSize);
		initiallyFillStorageWithItems(defaultStorageSize);
	}

	@Override
	public void run() {
		while(!isDestroyed()) {
			try {
				T material = producer.get();
				System.out.println("Consumed :" + material);
				Thread.sleep((long) (getProducingTime() / producingSpeed));
				if(!isDestroyed()) {
					U item = produceNewItem(material);
					System.out.println("Produced :" + item);
					store(item);
				}
			} catch (InterruptedException ignored) {
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
			throw new BuildingDestroyedException("It's not possible to attack destroyed building");
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

	public synchronized void store(U item) {
		storage.push(item);
		notifyAll();
	}

	public synchronized int getNumberOfItemsInStorage() {
		return storage.size();
	}

	@Override
	public synchronized U getItem() {
		waitForItemInStorage();
		if(isDestroyed()) {
			throw new BuildingDestroyedException();
		}
		return storage.pop();
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

	private synchronized void waitForItemInStorage() {
		while(storage.size() == 0 && !isDestroyed()) {
			try {
				wait();
			}
			catch (InterruptedException ignored) {
			}
		}
	}
}


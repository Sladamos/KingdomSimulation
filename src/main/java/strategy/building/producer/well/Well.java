package strategy.building.producer.well;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.Producer;
import strategy.product.Product;
import strategy.product.tool.bucket.Bucket;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Well<T extends Bucket, U extends Product> implements Producer {

	private final Deque<U> storage;

	private final double extractingSpeed;

	private final Supplier<T> materialProducer;

	@Getter(onMethod_={@Synchronized})
	private int durability;

	private boolean isDestroyed;

	public Well(Supplier<T> materialProducer, int defaultStorageSize, double extractingSpeed, int durability) {
		this.materialProducer = materialProducer;
		isDestroyed = false;
		this.durability = durability;
		this.extractingSpeed = extractingSpeed;
		storage = new ArrayDeque<>();
		checkInitParameters(defaultStorageSize);
		initiallyFillStorageWithItems(defaultStorageSize);
	}

	@Override
	public void run() {
		while(!isDestroyed()) {
			try {
				T material = materialProducer.get();
				System.out.println("Consumed :" + material);
				Thread.sleep((long) (getExtractingTime() / extractingSpeed));
				if(!isDestroyed()) {
					U item = extractNewItem(material);
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

	public synchronized U getItem() {
		waitForItemInStorage();
		if(isDestroyed()) {
			throw new BuildingDestroyedException();
		}
		return storage.pop();
	}

	protected abstract U extractNewItem(T material);

	protected abstract U extractNewItem();

	protected abstract int getExtractingTime();

	private void checkInitParameters(int storageSize) {
		if (storageSize < 0) {
			throw new IncorrectStorageException("Storage size must be a non negative number");
		}
	}

	private void initiallyFillStorageWithItems(int numberOfItems) {
		for(int i = 0; i < numberOfItems; i++) {
			U item = extractNewItem();
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


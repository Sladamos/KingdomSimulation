package strategy.producer;

import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import strategy.producer.exceptions.ProducerDestroyedException;
import strategy.producer.exceptions.IncorrectDamageException;
import strategy.producer.exceptions.IncorrectStorageException;
import strategy.producer.exceptions.ProducerTerminatedException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class OneToOneProducer<T, U> implements OneItemProducer<U> {

	private final Deque<U> storage;

	private final double producingSpeed;

	@Setter
	private Supplier<T> producer;

	@Getter(onMethod_={@Synchronized})
	private int durability;

	private boolean isDestroyed;

	private boolean isWorking;

	public OneToOneProducer(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		this.producer = producer;
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
		while(!isDestroyed() && isWorking()) {
			try {
				T material = producer.get();
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

	public synchronized void store(U item) {
		storage.push(item);
		notifyAll();
	}

	public synchronized int getNumberOfItemsInStorage() {
		return storage.size();
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
		while(storage.size() == 0 && !isDestroyed() && isWorking()) {
			try {
				wait();
			}
			catch (InterruptedException ignored) {
			}
		}
	}
}


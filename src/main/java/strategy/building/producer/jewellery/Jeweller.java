package strategy.building.producer.jewellery;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.Producer;
import strategy.material.Material;
import strategy.product.jewellery.Jewellery;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Jeweller<T extends Material, U extends Jewellery> implements Producer {

	private final Deque<U> storage;

	private final double craftingSpeed;

	private final Supplier<T> materialProducer;

	@Getter(onMethod_={@Synchronized})
	private int durability;

	private boolean isDestroyed;

	public Jeweller(Supplier<T> materialProducer, int defaultStorageSize, double craftingSpeed, int durability) {
		this.materialProducer = materialProducer;
		isDestroyed = false;
		this.durability = durability;
		this.craftingSpeed = craftingSpeed;
		storage = new ArrayDeque<>();
		checkInitParameters(defaultStorageSize);
		initiallyFillStorageWithJewelleries(defaultStorageSize);
	}

	@Override
	public void run() {
		while(!isDestroyed()) {
			try {
				T material = materialProducer.get();
				System.out.println("Consumed :" + material);
				Thread.sleep((long) (getCraftingTime() / craftingSpeed));
				if(!isDestroyed()) {
					U jewellery = createNewJewellery(material);
					System.out.println("Produced :" + jewellery);
					store(jewellery);
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

	public synchronized void store(U jewellery) {
		storage.push(jewellery);
		notifyAll();
	}

	public synchronized int getNumberOfJewelleriesInStorage() {
		return storage.size();
	}

	public synchronized U getJewellery() {
		waitForJewelleryInStorage();
		if(isDestroyed()) {
			throw new BuildingDestroyedException();
		}
		return storage.pop();
	}

	protected abstract U createNewJewellery(T material);

	protected abstract U createNewJewellery();

	protected abstract int getCraftingTime();

	private void checkInitParameters(int storageSize) {
		if (storageSize < 0) {
			throw new IncorrectStorageException("Storage size must be a non negative number");
		}
	}

	private void initiallyFillStorageWithJewelleries(int numberOfJewelleries) {
		for(int i = 0; i < numberOfJewelleries; i++) {
			U jewellery = createNewJewellery();
			store(jewellery);
		}
	}

	private synchronized void waitForJewelleryInStorage() {
		while(storage.size() == 0 && !isDestroyed()) {
			try {
				wait();
			}
			catch (InterruptedException ignored) {
			}
		}
	}
}



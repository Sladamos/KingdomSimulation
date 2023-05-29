package strategy.building.producer.farm;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.Producer;
import strategy.material.plant.Plant;
import strategy.product.fluid.Water;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Farm<T extends Water, U extends Plant> implements Producer {

	private final Deque<U> storage;

	private final double growingSpeed;

	private final Supplier<T> waterProducer;

	@Getter(onMethod_={@Synchronized})
	private int durability;

	private boolean isDestroyed;

	public Farm(Supplier<T> waterProducer, int defaultStorageSize, double growingSpeed, int durability) {
		this.waterProducer = waterProducer;
		isDestroyed = false;
		this.durability = durability;
		this.growingSpeed = growingSpeed;
		storage = new ArrayDeque<>();
		checkInitParameters(defaultStorageSize);
		initiallyFillStorageWithPlants(defaultStorageSize);
	}

	@Override
	public void run() {
		while(!isDestroyed()) {
			try {
				T material = waterProducer.get();
				System.out.println("Consumed :" + material);
				Thread.sleep((long) (getGrowingTime() / growingSpeed));
				if(!isDestroyed()) {
					U plant = createNewPlant(material);
					System.out.println("Produced :" + plant);
					store(plant);
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

	public synchronized void store(U plant) {
		storage.push(plant);
		notifyAll();
	}

	public synchronized int getNumberOfPlantsInStorage() {
		return storage.size();
	}

	public synchronized U getPlant() {
		waitForPlantInStorage();
		if(isDestroyed()) {
			throw new BuildingDestroyedException();
		}
		return storage.pop();
	}

	protected abstract U createNewPlant(T material);

	protected abstract U createNewPlant();

	protected abstract int getGrowingTime();

	private void checkInitParameters(int storageSize) {
		if (storageSize < 0) {
			throw new IncorrectStorageException("Storage size must be a non negative number");
		}
	}

	private void initiallyFillStorageWithPlants(int numberOfPlants) {
		for(int i = 0; i < numberOfPlants; i++) {
			U plant = createNewPlant();
			store(plant);
		}
	}

	private synchronized void waitForPlantInStorage() {
		while(storage.size() == 0 && !isDestroyed()) {
			try {
				wait();
			}
			catch (InterruptedException ignored) {
			}
		}
	}
}



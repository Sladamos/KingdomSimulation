package strategy.building.producer.livestock;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.Producer;
import strategy.material.food.Food;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class LivestockAnimal<T extends Food> implements Producer {

	private final Deque<T> storage;

	private final double producingSpeed;

	@Getter(onMethod_={@Synchronized})
	private int durability;

	private boolean isDestroyed;

	public LivestockAnimal(int defaultStorageSize, double producingSpeed, int durability) {
		isDestroyed = false;
		this.durability = durability;
		this.producingSpeed = producingSpeed;
		storage = new ArrayDeque<>();
		checkInitParameters(defaultStorageSize);
		initiallyFillStorageWithFoods(defaultStorageSize);
	}

	@Override
	public void run() {
		while(!isDestroyed()) {
			try {
				Thread.sleep((long) (19000 / producingSpeed));
				if(!isDestroyed()) {
					T food = createNewFood();
					System.out.println("Produced :" + food);
					store(food);
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

	public synchronized void store(T food) {
		storage.push(food);
		notifyAll();
	}

	public synchronized int getNumberOfFoodsInStorage() {
		return storage.size();
	}

	public synchronized T getFood() {
		waitForFoodInStorage();
		if(isDestroyed()) {
			throw new BuildingDestroyedException();
		}
		return storage.pop();
	}

	protected abstract T createNewFood();

	private void checkInitParameters(int storageSize) {
		if (storageSize < 0) {
			throw new IncorrectStorageException("Storage size must be a non negative number");
		}
	}

	private void initiallyFillStorageWithFoods(int numberOfFoods) {
		for(int i = 0; i < numberOfFoods; i++) {
			T food = createNewFood();
			store(food);
		}
	}

	private synchronized void waitForFoodInStorage() {
		while(storage.size() == 0 && !isDestroyed()) {
			try {
				wait();
			}
			catch (InterruptedException ignored) {
			}
		}
	}
}

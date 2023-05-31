package strategy.producer.building.livestock;

import strategy.producer.ZeroToOneProducer;
import strategy.material.food.Food;
import strategy.producer.building.Building;

public abstract class LivestockAnimal<T extends Food> extends ZeroToOneProducer<T> implements Building {

	public LivestockAnimal(int defaultStorageSize, double producingSpeed, int durability) {
		super(defaultStorageSize, producingSpeed, durability);
	}

	public synchronized T getFood() {
		return getItem();
	}

	@Override
	protected int getProducingTime() {
		return 19000;
	}
}
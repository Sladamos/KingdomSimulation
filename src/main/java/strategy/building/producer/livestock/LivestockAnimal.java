package strategy.building.producer.livestock;

import strategy.building.producer.ZeroToOneProducer;
import strategy.material.food.Food;

public abstract class LivestockAnimal<T extends Food> extends ZeroToOneProducer<T> {

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
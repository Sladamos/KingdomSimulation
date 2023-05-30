package strategy.building.producer.farm;

import strategy.building.producer.OneToOneProducer;
import strategy.material.plant.Plant;
import strategy.product.fluid.Water;

import java.util.function.Supplier;

public abstract class Farm<T extends Water, U extends Plant> extends OneToOneProducer<T, U> {

	public Farm(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		super(producer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized U getPlant() {
		return getItem();
	}
}
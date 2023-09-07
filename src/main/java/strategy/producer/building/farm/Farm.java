package strategy.producer.building.farm;

import strategy.producer.OneToOneProducer;
import strategy.item.plant.Plant;
import strategy.producer.building.Building;
import strategy.item.fluid.Water;

import java.util.function.Supplier;

public abstract class Farm<T extends Water, U extends Plant> extends OneToOneProducer<T, U> implements Building {

	public Farm(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		super(producer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized U getPlant() {
		return getItem();
	}
}
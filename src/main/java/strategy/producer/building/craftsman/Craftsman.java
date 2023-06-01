package strategy.producer.building.craftsman;

import strategy.producer.OneToOneProducer;

import java.util.function.Supplier;

public abstract class Craftsman<T, S> extends OneToOneProducer<T, S> {

	public Craftsman(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		super(producer, defaultStorageSize, producingSpeed, durability);
	}
}

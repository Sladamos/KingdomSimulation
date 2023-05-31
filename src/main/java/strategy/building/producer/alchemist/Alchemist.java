package strategy.building.producer.alchemist;

import strategy.building.producer.TwoToOneProducer;
import strategy.product.elixir.Elixir;

import java.util.function.Supplier;

public abstract class Alchemist<T, U, V extends Elixir> extends TwoToOneProducer<T, U, V> {

	public Alchemist(Supplier<T> firstProducer, Supplier<U> secondProducer, int defaultStorageSize, double producingSpeed, int durability) {
		super(firstProducer, secondProducer, defaultStorageSize, producingSpeed, durability);
	}

	@Override
	protected int getProducingTime() {
		return 27000;
	}
}

package strategy.producer.building.alchemist;

import strategy.producer.TwoToOneProducer;
import strategy.producer.building.Building;
import strategy.item.elixir.Elixir;

import java.util.function.Supplier;

public abstract class Alchemist<T, U, V extends Elixir> extends TwoToOneProducer<T, U, V> implements Building {

	public Alchemist(Supplier<T> firstProducer, Supplier<U> secondProducer, int defaultStorageSize, double producingSpeed, int durability) {
		super(firstProducer, secondProducer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized V getElixir() {
		return getItem();
	}

	@Override
	protected int getProducingTime() {
		return 27000;
	}
}

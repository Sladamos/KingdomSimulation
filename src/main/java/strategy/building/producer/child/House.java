package strategy.building.producer.child;

import strategy.building.producer.OneToOneProducer;
import strategy.organism.human.Human;
import strategy.product.coin.Coin;

import java.util.function.Supplier;

public abstract class House<T extends Coin, U extends Human> extends OneToOneProducer<T, U> {

	public House(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		super(producer, defaultStorageSize, producingSpeed, durability);
	}

	@Override
	protected int getProducingTime() {
		return 0;
	}
}

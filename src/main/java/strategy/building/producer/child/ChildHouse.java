package strategy.building.producer.child;

import strategy.building.producer.OneToOneProducer;
import strategy.organism.human.Child;
import strategy.product.coin.Coin;

import java.util.function.Supplier;

public class ChildHouse<T extends Coin> extends House<T, Child> {

	private static final int CHILD_HOUSE_DURABILITY = 100;

	private static final int CHILD_HOUSE_PRODUCING_SPEED = 1;

	public ChildHouse(Supplier<T> producer, int defaultStorageSize) {
		super(producer, defaultStorageSize, CHILD_HOUSE_PRODUCING_SPEED,
				CHILD_HOUSE_DURABILITY);
	}

	@Override
	protected Child produceNewItem(T material) {
		return new Child(0, 100);
	}

	@Override
	protected Child produceNewItem() {
		return new Child(0, 100);
	}

	@Override
	protected int getProducingTime() {
		return 50000;
	}
}

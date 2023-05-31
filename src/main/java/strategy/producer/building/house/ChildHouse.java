package strategy.producer.building.house;

import strategy.organism.human.Child;
import strategy.product.coin.Coin;
import strategy.product.food.baking.bread.Bread;

import java.util.function.Supplier;

public class ChildHouse<T extends Coin, U extends Bread> extends House<T, U, Child> {

	private static final int CHILD_HOUSE_DURABILITY = 100;

	private static final int CHILD_HOUSE_PRODUCING_SPEED = 1;

	public ChildHouse(Supplier<T> firstProducer, Supplier<U> secondProducer, int defaultStorageSize) {
		super(firstProducer, secondProducer, defaultStorageSize, CHILD_HOUSE_PRODUCING_SPEED,
				CHILD_HOUSE_DURABILITY);
	}

	@Override
	protected Child produceNewItem(T material, U secondMaterial) {
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

package strategy.building.producer.human;

import strategy.building.producer.TwoToOneProducer;
import strategy.organism.human.Human;
import strategy.product.coin.Coin;
import strategy.product.food.Food;

import java.util.function.Supplier;

public abstract class House<T extends Coin, U extends Food, V extends Human> extends TwoToOneProducer<T, U, V> {

	public House(Supplier<T> firstProducer, Supplier<U> secondProducer, int defaultStorageSize, double producingSpeed, int durability) {
		super(firstProducer, secondProducer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized V getHuman() {
		return getItem();
	}
}

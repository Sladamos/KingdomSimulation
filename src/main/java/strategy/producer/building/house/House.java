package strategy.producer.building.house;

import strategy.producer.TwoToOneProducer;
import strategy.organism.human.Human;
import strategy.producer.building.Building;
import strategy.item.coin.Coin;
import strategy.item.Food;

import java.util.function.Supplier;

public abstract class House<T extends Coin, U extends Food, V extends Human> extends TwoToOneProducer<T, U, V>
		implements Building {

	public House(Supplier<T> firstProducer, Supplier<U> secondProducer, int defaultStorageSize, double producingSpeed, int durability) {
		super(firstProducer, secondProducer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized V getHuman() {
		return getItem();
	}
}

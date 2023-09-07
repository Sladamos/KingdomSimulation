package strategy.producer.building.house;

import strategy.item.Food;
import strategy.item.coin.Coin;
import strategy.item.organism.human.Human;
import strategy.producer.ProducerConfig;
import strategy.producer.TwoToOneProducer;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class House<T extends Coin, U extends Food, V extends Human> extends TwoToOneProducer<T, U, V>
		implements Building {

	public House(OneItemStorage<T> firstSourceStorage, OneItemStorage<U> secondSourceStorage, OneItemStorage<V> destinationStorage, ProducerConfig producerConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
	}
}

package strategy.producer.building.alchemist;

import strategy.item.Item;
import strategy.item.elixir.Elixir;
import strategy.producer.ProducerConfig;
import strategy.producer.TwoToOneProducer;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Alchemist<T extends Item, U extends Item, V extends Elixir> extends TwoToOneProducer<T, U, V> implements Building {

	public Alchemist(OneItemStorage<T> firstSourceStorage, OneItemStorage<U> secondSourceStorage, OneItemStorage<V> destinationStorage, ProducerConfig producerConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
	}
}

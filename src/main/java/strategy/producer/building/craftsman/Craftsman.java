package strategy.producer.building.craftsman;

import strategy.item.Item;
import strategy.producer.OneToOneProducer;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public abstract class Craftsman<T extends Item, S extends Item> extends OneToOneProducer<T, S> {

	public Craftsman(OneItemStorage<T> sourceStorage, OneItemStorage<S> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}
}

package strategy.producer.building.farm;

import strategy.item.fluid.Water;
import strategy.item.plant.Plant;
import strategy.producer.OneToOneProducer;
import strategy.producer.ProducerConfig;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Farm<T extends Water, U extends Plant> extends OneToOneProducer<T, U> implements Building {

	public Farm(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}
}
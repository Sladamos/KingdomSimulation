package strategy.producer.building.livestock;

import strategy.producer.ProducerConfig;
import strategy.producer.ZeroToOneProducer;
import strategy.item.Food;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class LivestockAnimal<T extends Food> extends ZeroToOneProducer<T> implements Building {
	public LivestockAnimal(OneItemStorage<T> destinationStorage, ProducerConfig producerConfig) {
		super(destinationStorage, producerConfig);
	}
}
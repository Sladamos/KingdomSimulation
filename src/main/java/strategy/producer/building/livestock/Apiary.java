package strategy.producer.building.livestock;

import strategy.item.food.Honey;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class Apiary extends LivestockAnimal<Honey> {

	public Apiary(OneItemStorage<Honey> destinationStorage, ProducerConfig producerConfig) {
		super(destinationStorage, producerConfig);
	}

	@Override
	protected Honey createNewItem() {
		return new Honey();
	}
}

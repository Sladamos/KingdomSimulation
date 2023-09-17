package strategy.producer.building.livestock;

import strategy.item.food.Milk;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class Cow extends LivestockAnimal<Milk> {

	public Cow(OneItemStorage<Milk> destinationStorage, ProducerConfig producerConfig) {
		super(destinationStorage, producerConfig);
	}

	@Override
	protected Milk createNewItem() {
		return new Milk();
	}
}

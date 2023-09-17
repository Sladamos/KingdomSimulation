package strategy.producer.building.farm;

import strategy.item.fluid.Water;
import strategy.item.plant.Wheat;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class WheatFarm extends Farm<Water, Wheat> {

	public WheatFarm(OneItemStorage<Water> sourceStorage, OneItemStorage<Wheat> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected Wheat createNewItem(Water water) {
		return new Wheat();
	}
}

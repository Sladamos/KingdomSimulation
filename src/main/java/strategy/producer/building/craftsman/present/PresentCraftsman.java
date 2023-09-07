package strategy.producer.building.craftsman.present;

import strategy.item.Item;
import strategy.item.present.Present;
import strategy.producer.ProducerConfig;
import strategy.producer.building.craftsman.Craftsman;
import strategy.storage.OneItemStorage;

public abstract class PresentCraftsman<T extends Item, S extends Present> extends Craftsman<T, S> {

	public PresentCraftsman(OneItemStorage<T> sourceStorage, OneItemStorage<S> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}
}

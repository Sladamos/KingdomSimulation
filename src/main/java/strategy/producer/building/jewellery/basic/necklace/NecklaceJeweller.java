package strategy.producer.building.jewellery.basic.necklace;

import strategy.item.Item;
import strategy.item.jewellery.necklace.Necklace;
import strategy.producer.ProducerConfig;
import strategy.producer.building.jewellery.basic.Jeweller;
import strategy.storage.OneItemStorage;

public abstract class NecklaceJeweller<T extends Item, U extends Necklace> extends Jeweller<T, U> {

	public NecklaceJeweller(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}
}

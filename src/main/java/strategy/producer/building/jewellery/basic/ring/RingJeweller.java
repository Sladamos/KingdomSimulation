package strategy.producer.building.jewellery.basic.ring;

import strategy.item.Item;
import strategy.item.jewellery.ring.Ring;
import strategy.producer.ProducerConfig;
import strategy.producer.building.jewellery.basic.Jeweller;
import strategy.storage.OneItemStorage;

public abstract class RingJeweller<T extends Item, U extends Ring> extends Jeweller<T, U> {

	public RingJeweller(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}
}

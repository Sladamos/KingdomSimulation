package strategy.producer.building.jewellery.basic;

import strategy.item.Item;
import strategy.item.jewellery.Jewellery;
import strategy.producer.OneToOneProducer;
import strategy.producer.ProducerConfig;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Jeweller<T extends Item, U extends Jewellery> extends OneToOneProducer<T, U>  implements Building {

	public Jeweller(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}
}



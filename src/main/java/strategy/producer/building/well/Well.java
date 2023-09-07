package strategy.producer.building.well;

import strategy.item.Item;
import strategy.item.tool.bucket.Bucket;
import strategy.producer.OneToOneProducer;
import strategy.producer.ProducerConfig;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Well<T extends Bucket, U extends Item> extends OneToOneProducer<T, U> implements Building {

	public Well(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}
}


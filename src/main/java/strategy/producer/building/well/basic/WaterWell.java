package strategy.producer.building.well.basic;

import strategy.item.fluid.Water;
import strategy.item.tool.bucket.IronBucket;
import strategy.producer.ProducerConfig;
import strategy.producer.building.well.Well;
import strategy.storage.OneItemStorage;

public class WaterWell extends Well<IronBucket, Water> {

	public WaterWell(OneItemStorage<IronBucket> sourceStorage, OneItemStorage<Water> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected Water createNewItem(IronBucket material) {
		return new Water();
	}
}

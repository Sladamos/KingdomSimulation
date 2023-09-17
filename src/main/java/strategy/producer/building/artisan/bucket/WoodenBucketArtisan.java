package strategy.producer.building.artisan.bucket;

import strategy.item.tool.bucket.WoodenBucket;
import strategy.item.wood.Mahogany;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class WoodenBucketArtisan extends BucketArtisan<Mahogany, WoodenBucket> {

	public WoodenBucketArtisan(OneItemStorage<Mahogany> sourceStorage, OneItemStorage<WoodenBucket> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected WoodenBucket createNewItem(Mahogany material) {
		return new WoodenBucket();
	}
}

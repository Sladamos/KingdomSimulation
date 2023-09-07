package strategy.producer.building.artisan.bucket;

import strategy.item.wood.Mahogany;
import strategy.item.tool.bucket.WoodenBucket;

import java.util.function.Supplier;

public class WoodenBucketArtisan extends BucketArtisan<Mahogany, WoodenBucket> {

	private static final int WOODEN_BUCKET_ARTISAN_DURABILITY = 100;

	private static final int WOODEN_BUCKET_ARTISAN_CRAFTING_SPEED = 4;
	public WoodenBucketArtisan(Supplier<Mahogany> materialProducer, int defaultStorageSize) {
		super(materialProducer, defaultStorageSize, WOODEN_BUCKET_ARTISAN_CRAFTING_SPEED,
				WOODEN_BUCKET_ARTISAN_DURABILITY);
	}

	@Override
	protected WoodenBucket createNewItem(Mahogany material) {
		return new WoodenBucket();
	}

	@Override
	protected WoodenBucket produceNewItem() {
		return new WoodenBucket();
	}
}
